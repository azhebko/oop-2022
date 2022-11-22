package oop.y2022.lambda;

/** Обычная реализация интерфейса. */
public class CommandImpl implements Command {

    @Override
    public void process(Object data, long timeout) {
        System.out.println("Processing");
    }

    /** Переопределяем реализацию по умолчанию, расширяя дополнительным логированием. */
    @Override
    public void process(Object data) {
        Command.super.process(data);
        System.out.println("Extended processing");
    }
}
