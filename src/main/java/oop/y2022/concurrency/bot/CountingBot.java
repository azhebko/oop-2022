package oop.y2022.concurrency.bot;

import java.util.concurrent.atomic.AtomicInteger;

/** Бот, подсчитывающий запросы. */
public class CountingBot implements IBot {

    private int requestsCount = 0;

    // неблокирующий атомарный апдейт инта
    private AtomicInteger requestsAtomicCount = new AtomicInteger();

    @Override
    public String getCode() {
        return "CountingBot";
    }

    /**
     * Ключевое слово synchronized обеспечивает исключительный доступ каждому потоку для выполнения метода.
     * Как правило, есть более эффективные способы достичь корректного многопоточного исполнения.
     */
    @Override
    public /*synchronized*/ void onRequest(Request request) {
        // synchronized-блок на все тело метода эквивалентно synchronized-методу
//        synchronized (this) {
        // можно ограничить synchronized-блок - давать
        // исключительный доступ лишь на часть метода

        // инкремент поля класса состоит из трех операций
//        requestsCount++;
        int reqCountLocal = requestsCount; // I - чтение значения поля
        int newValue = reqCountLocal + 1; // II - сложение
        requestsCount = newValue; // III - запись нового значение в поле

        // при конкурентной обработке есть шанс последовательного выполнения
        // t1-I t1-II t1-III t2-I t2-II t2-III
        // результатом станет значение "2" в счетчике

        // однако если выполнение будет выглядеть так
        // t1-I t2-I t1-II t2-II t1-III t2-III
        // то оба потока считают значение "0", затем каждый независимо получит значение "1"
        // затем оба запишут значений "1" в поле, что и станет итогом

        // атомарный неблокирующий инкремент
        requestsAtomicCount.incrementAndGet();
//        }
    }
}
