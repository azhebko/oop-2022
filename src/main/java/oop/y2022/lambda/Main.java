package oop.y2022.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        // локальный класс, объявляется внутри метода, доступен только в нем
        class StrategImpl implements Strategy {
            @Override
            public void onEvent(Object event) {
                // omitted
            }

            @Override
            public void onException(Throwable exception) {
                // omitted
            }
        }

        // анонимный класс, реализация интерфейса Command
        // тот же локальный, только без имени
        Command cmd = new Command() {
            @Override
            public void process(Object data, long timeout) {
                // omitted
            }
        };

        // еще один анонимный класс
        Strategy strategy = new Strategy() {

            // можем объявлять поля...
            private String id;

            @Override
            public void onEvent(Object event) {
                id = event.toString();
                preDispatch(event);
                System.out.println("Event");
            }

            @Override
            public void onException(Throwable exception) {
                preDispatch(exception);
                System.out.println("Exception");
            }

            // ... и методы
            private void preDispatch(Object data) {
                // omitted
            }
        };

        // пример использования статического метода интерфейса
        Command.logDebugInfo();

        // переменную с типом-функциональным интерфейсом можно инициализировать лямбда-выражением
        // если совпадают параметры и возвращаемый тип (лямбды с абстрактным методом функционального интерфейса)
        Command cmdLambda = (data, timeout) -> System.out.println("Processing");

        // одинаковое лямбда-выражение можно присвоить к переменными разных типов
        Runnable runnable = () -> System.out.println("Hello, world!");
        ExtRunnable extRunnable = () -> System.out.println("Hello, world!");

        // при этом переменные не становятся совместимы между собой - из-за разных типов
//        runnable = extRunnable;
//        extRunnable = runnable;

        // нельзя присвоить лямбда-выражение в переменную типа Object - он не функциональный интерфейс
//        Object obj = () -> System.out.println("Hello, world!");

        // в теле лямбды можно бросать проверяемое исключение, поскольку в абстрактном методе
        // функционального типа есть соответствующее исключение в блоке throws
        ExtRunnable extRunnableException = () -> {
            throw new Exception();
        };

        // ошибка компиляции, в абстрактном методе run нет блока throws
//        Runnable runnableException = () -> {
//            throw new Exception();
//        };

        // передаем лямбда-выражение в аргумент метода, тип выводится
        processCommand((data, timeout) -> System.out.println("Process"));

        // method reference

        // передаем ссылку на метод
        processCommand(Main::defaultProcessImplementation);

        // constructor reference
        @SuppressWarnings("all")
        Function<String,Integer> stringParserConstructor = Integer::new;

        // static method reference
        Function<String,Integer> stringParserFactoryMethod = Integer::parseInt;

        // метод сложения двух целых чисел можно привести к BiFunction...
        BiFunction<Integer, Integer, Integer> reduceFunction = Integer::sum;

        // ... а также к IntBinaryOperator
        IntBinaryOperator reduceOperator1 = Integer::sum;

        // выбор наиболшего целого числа из двух кандидатов также приводится к IntBinaryOperator
        IntBinaryOperator reduceOperator2 = Integer::max;

        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(reduceOperator1)
                .ifPresent(System.out::println);

        // instance method reference - bound receiver
        Predicate<String> stringPredicate = "foobarbaz"::contains;

        // фильтруем подстроки 'foobarbaz'
        Stream.of("foo", "bar", "baz", "foobar", "foobaz", "barbaz")
                .filter(stringPredicate)
                .forEach(System.out::println);

        // instance method reference - unbound receiver
        Comparator<Integer> compare = Integer::compare;
        Comparator<Integer> compareTo = Integer::compareTo;
        Stream.of("foo", "bar", "baz", "foobar", "foobaz", "barbaz")
                .map(String::length)
                .max(compareTo)
                .ifPresent(System.out::println);
    }

    private static void processCommand(Command command) {
        // omitted
        command.process(new Object(), 10L);
        // omitted
    }

    private static void defaultProcessImplementation(Object data, long timeout) {
        // omitted
    }

    // перегруженный метод не проблема для компилятора, сигнатура отличается
    private static void defaultProcessImplementation() {
        // omitted
    }
}
