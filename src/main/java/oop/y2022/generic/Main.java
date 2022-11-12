package oop.y2022.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Container
        // stringContainer использует параметр String - чтение и запись только объектов типа String
        Container<String> stringContainer = new Container<>();
        stringContainer.setValue("test string value");
        String value = stringContainer.getValue();

        // Collection
        // statistic использует параметр Long - добавление, получение элементов типа Long
        List<Long> statistic = new ArrayList<>();
        statistic.add(12345L);
        Long firstStatisticValue = statistic.get(0);

        // Covariance
        List<StringBuilder> builders = Arrays.asList(
                new StringBuilder("first"),
                new StringBuilder("second"),
                new StringBuilder("first")
        );

        List<String> strings = Arrays.asList(
                "first",
                "second",
                "first"
        );

        CommonPrinter commonPrinter = new CommonPrinter();
        // можно печатать по одному - String, StringBuilder
        commonPrinter.printCharSequence(builders.get(0));
        commonPrinter.printCharSequence(strings.get(0));

        // нельзя печатать списком, printCharSequences ожидает List<CharSequence> - ошибка компиляции
        // параметризованные типы инвариантны и не наследуются при наследовании
        // параметров (String наследует CharSequence)
//        commonPrinter.printCharSequences(builders);
//        commonPrinter.printCharSequences(strings);

        // используем ковариантность - метод печати принимает список,
        // параметризованный любым типом - наследником CharSequence
        commonPrinter.print(builders);
        commonPrinter.print(strings);

        // использует параметр StringBuilder
        Printer<StringBuilder> stringBuilderPrinter = new Printer<>();
        // можем печатать список StringBuilder
        stringBuilderPrinter.print(builders);

        // нельзя печатать список String, ошибка компиляции
//        stringBuilderPrinter.print(strings);

        // использует параметр String
        // противоположная ситуация
        Printer<String> stringPrinter = new Printer<>();
//        stringPrinter.print(builders);
        stringPrinter.print(strings);

        // Contravariance
        List<Integer> integers = new ArrayList<>();
        List<Long> longs = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        NumbersFiller numbersFiller = new NumbersFiller();
//        нельзя передавать инты - параметром должен быть над-типом Number
//        numbersFiller.fill(integers);
//        нельзя передавать лонги - параметром должен быть над-типом Number
//        numbersFiller.fill(longs);

        // можно передавать Number-ы
        numbersFiller.fill(numbers);

        // можно передавать Object-ы
        numbersFiller.fill(objects);

        // тип переменной Number, фактический тип - один из его наследников, приведение корректно
        Number number = numbers.get(4);
        numbers.add(1234);
    }

    /**
     * Общий принтер.
     * Позволяет печатать подготовленную последовательность символов - строки, стринг-билдеры и прочее.
     */
    public static class CommonPrinter {

        /**
         * Напечатать одну последовательность символов.
         * Можно передавать любую реализацию CharSequence: String, StringBuilder.
         */
        void printCharSequence(CharSequence item) {
            System.out.println(item);
        }

        /**
         * Напечатать несколько последовательностей символов.
         * Можно передавать ТОЛЬКО список, параметризованный CharSequence.
         */
        void printCharSequences(List<CharSequence> items) {
            items.forEach(System.out::println);

            // в теле метода возможны чтения и добавления в список
            CharSequence charSequence = items.get(0);

            // например, такая операция объясняет почему невозможно передать
            // фактическим параметром List<StringBuilder>.
            // String - реализация CharSequence, использование в List::add корректно.
            items.add("");
        }

        /**
         * Напечатать несколько последовательностей символов.
         * Можно передавать список, параметризованный любой реализацией CharSequence.
         */
        void print(List<? extends CharSequence> items) {
            // чтение корректно.
            // добавление элементов в список невозможно, настоящий тип списка неизвестен.
            for (CharSequence item : items) {
                System.out.println(item);
            }
        }
    }

    /** Параметризация возможного типа списка на печать вынесена на уровень класса принтера. */
    public static class Printer<T extends CharSequence> {
        void print(List<T> items) {
            items.forEach(System.out::println);
        }
    }

    /** Заполнение списка заготовленными числами. */
    public static class NumbersFiller {

        /**
         * Напечатать несколько последовательностей символов.
         * Можно передавать список, параметризованный любым над-типом Number.
         */
        private void fill(List<? super Number> numbers) {
            // добавление элементов корректно.
            // чтение элементов списка невозможно, настоящий тип списка неизвестен.
            numbers.add(42);
            numbers.add(1_000_000_000_000L);
            numbers.add(1.0D);
            numbers.add(Integer.valueOf(123));

            // можно записывать только числа (объекты наследники Number)
//            numbers.add("");
//            numbers.add(new Object());
        }
    }
}
