package oop.y2022.lambda;

/** Нефункциональный интерфейс - содержит больше одного абстрактного метода */
public interface Strategy {

    void onEvent(Object event);

    void onException(Throwable exception);
}
