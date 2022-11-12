package oop.y2022.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Unit-тесты сервиса проверки четности. */
public class MyServiceTest {

    private MyService myService = new MyService();

    /** Тест проверки случая ноль - четное. */
    @Test
    public void testZeroIsEven() {
        Assertions.assertTrue(myService.isEven(0));
    }

    /** Тест проверки случая пятьдесят четыре - четное. */
    @Test
    public void testFiftyFourIsEven() {
        Assertions.assertTrue(myService.isEven(54));
    }

    /** Тест проверки случая минус три - нечетное. */
    @Test
    public void testMinusThreeIsEven() {
        Assertions.assertFalse(myService.isEven(-3));
    }

}
