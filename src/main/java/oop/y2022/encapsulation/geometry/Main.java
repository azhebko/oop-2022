package oop.y2022.encapsulation.geometry;

import oop.y2022.encapsulation.geometry.authorization.Authorization;
import oop.y2022.encapsulation.geometry.authorization.User;

public class Main {

    private final Authorization authorization = new Authorization();

    public static void main(String[] args) {
        new Main().process();
    }

    public void process() {
        // аутентификация пользователя, сохранение прав в объекте авторизации
        authorization.login(new User(1, "test", Authorization.ANY_PERMISSION));

        // обращаемся к объекту 'квадрат' через переменную типа 'прямоугольник',
        // последующий код полиморфен
        Rectangle rectangle = new Square(authorization);

        // прямой доступ к полям - плохо: ломаем инварианты длин сторон квадрата
        rectangle.length = 3;
        rectangle.width = 1;

        // так хорошо, длины сторон квадрата в порядке
        rectangle.setLength(3);
        rectangle.setWidth(1);

        // прямой доступ к полям - плохо: тиражируем формулу по всем местам использования, провоцируем ошибку
        boolean fitToAreaViaFields = 2 - 0 >= rectangle.length && 4 - 0 >= rectangle.width;
        System.out.println(fitToAreaViaFields);

        // так хорошо, формула написана и покрыта тестами в одном месте
        boolean fitToAreaViaMethod = rectangle.isFitToArea(0, 0, 2, 4);
        System.out.println(fitToAreaViaMethod);


        // прямой доступ к полям - плохо:
        // * нет проверки прав доступа пользователя
        // * не учитываем оптимизации отдельных реализаций прямоугольника
        rectangle.length = rectangle.length + rectangle.width;
        rectangle.width = rectangle.length - rectangle.width;
        rectangle.length = rectangle.length - rectangle.width;

        // так хорошо, проверяем права доступа пользователя,
        // при необходимости метод может быть оптимизирован в отдельных реализациях
        rectangle.rotate();

        // удаляем сессию пользователя
        authorization.logout();
    }
}
