package oop.y2022.unit;

/** Сервис, проверка четности чисел. */
public class MyService {
    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    /** Тест клиент. */
    public static void main(String[] args) {
        MyService myService = new MyService();
        assert myService.isEven(1);
        System.out.println(myService.isEven(1));
    }
}
