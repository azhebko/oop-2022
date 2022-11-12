package oop.y2022.concurrency.bot;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.function.Function;
import java.util.function.Supplier;

/** Реестр ботов обработчиков запросов. */
public class BotRegistry {

    // зарегистрированные боты
    private final Map<String,IBot> bots = new HashMap<>();

    // компонент, предоставляющий запросы с сервера
    private final Supplier<List<Request>> requestsProvider = () -> Collections.emptyList();

    // фабрика потоков, предоставляет потоки (замена new Thread())
    private final ThreadFactory threadFactory = Thread::new;

    public BotRegistry() {
        new Thread(() -> {
            // опрос сервера в цикле
            while (true) {
                for (Request request : requestsProvider.get()) {
                    IBot bot = bots.get(request.getBotCode());
                    if (bot != null) {
                        // каждый запрос обрабатывается в отдельном потоке
                        threadFactory.newThread(() -> bot.onRequest(request));
                    }
                }
            }
        }).start();
    }

    /** Регистрируем бота.  */
    public void register(IBot bot) {
        bots.put(bot.getCode(), bot);
    }
}
