package oop.y2022.lambda;

/**
 * Функциональный интерфейс - содержит один абстрактный метод.
 * Можно опционально промаркировать интерфейсом @FunctionalInterface.
 */
@FunctionalInterface
public interface Command {

    /** Абстрактный метод. */
    void process(Object data, long timeout);

    /** Метод с реализацией по умолчанию. */
    default void process(Object data) {
        logDebugInfoInner();
        process(data, 0L);
    }

    /** Статический метод, можно использовать внутри и снаружи интерфейса. */
    static void logDebugInfo() {
        logDebugInfoInner();
    }

    /** Статический приватный метод, такой статический, но недоступен снаружи интерфейса. */
    private static void logDebugInfoInner() {
        System.out.println("debug info");
    }
}
