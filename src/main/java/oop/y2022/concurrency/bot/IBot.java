package oop.y2022.concurrency.bot;

/** Бот-слушатель, обрабатывающий команды. */
public interface IBot {

    /** Селектор запросов. */
    String getCode();

    /** Оброботчик запроса. */
    void onRequest(Request request);
}
