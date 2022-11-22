package oop.y2022.lambda;

/** Копия java.lang.Runnable, только метод run может выбрасывать проверяемые исключения. */
public interface ExtRunnable {
    void run() throws Exception;
}
