package oop.y2022.unit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Параметризованные тесты.
 * Удобно использовать в случае, когда тело тест метода повторяется для всех случаев,
 * отличие только в параметрах и результате выполнения.
 */
public class MyServiceParamTest {

    private final MyService myService = new MyService();

    /**
     * Параметризованный тест объявляется спец аннотацией ParameterizedTest.
     * В аннотацию можно передать шаблон имени теста: при запуске будут подставлены фактические параметры.
     * В методе можно объявить параметры (метода и теста).
     * Для прогона тест-кейсов нужен источник данных.
     * При использовании примитивов в параметрах подойдет CsvSource - в нем задается список строк - кейсов
     * теста, каждая строка - кортеж данных, разделенных запятой (разделитель настраивается).
     * В данных можно передавать фактические параметры тестируемого метода, ожидаемый результат и любые другие сведения.
     */
    @ParameterizedTest(name = "Test number {0} even is {1}")
    @CsvSource(value={
            "0,true,test test 2",
            "1,false,test",
            "2,true,test",
            "3,false,test"
    })
    public void test(int number, boolean expectedResult, String label) {
        // метка теста, выводим в консоль
        System.out.println(label);
        // сравниваем ожидаемый результат с фактическим на переданных параметрах
        Assertions.assertEquals(expectedResult, myService.isEven(number));
    }

    /**
     * Для сложных параметров (например, список строк) потребуется использовать MethodSource.
     * Параметры теста задаются императивно в соседнем методе в виде Stream-а Arguments.
     * Аргументс - универсальная обертка данных, самостоятельно раскладывающаяся в параметры метода исполнения теста.
     */
    @MethodSource(value = "getArguments")
    @ParameterizedTest(name = "Test number {0} is even")
    public void testIsEven(int number, List<String> labels) {
        labels.forEach(System.out::println);

        Assertions.assertTrue(myService.isEven(number));
    }

    /** MethodSource все еще можно использовать с примитивными типами. */
    private static Stream<Integer> getArgumentsAsInt() {
        return Arrays.stream((new Integer[] { 0, 2, 4, 6 }));
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(0, Arrays.asList("test", "test 0")),
                Arguments.of(2, Arrays.asList("test", "test 2")),
                Arguments.of(4, Arrays.asList("test", "test 4")),
                Arguments.of(6, Arrays.asList("test", "test 6"))
        );
    }
}
