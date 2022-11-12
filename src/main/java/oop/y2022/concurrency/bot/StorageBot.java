package oop.y2022.concurrency.bot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** Бот-хранитель полезной нагрузки запросов. */
public class StorageBot implements IBot {

//    private final List<byte[]> storage = Collections.synchronizedList(new ArrayList<>());
    private final List<byte[]> storage = new CopyOnWriteArrayList<>();

    @Override
    public String getCode() {
        return "StorageBot";
    }

    @Override
    public void onRequest(Request request) {
        // нельзя использовать ArrayList в многопоточной обработке,
        // внутри непотокобезопасный доступ к индексу крайнего элемента.

        // вариант 0: synchronized-метод, synchronized-блок
        // вариант 1: обертка Collections.synchronizedList,
        // проксирующий запросы к оригинальному списку,
        // но все методы которого синхронизированы
        // вариант 2: потокобезопасные коллекции, например CopyOnWriteList
        storage.add(request.getPayload());
    }
}
