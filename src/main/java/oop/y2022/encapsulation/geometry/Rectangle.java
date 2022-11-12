package oop.y2022.encapsulation.geometry;

import oop.y2022.encapsulation.geometry.authorization.Authorization;
import oop.y2022.encapsulation.geometry.authorization.AuthorizationException;

public class Rectangle {

    // допустимо объявлять поле protected для переиспользования при наследовании
    protected final Authorization authorization;

    // поля класса открыты, так делать не стоит.
    // при записи значений в поля невозможно проверить допустимы ли значения,
    // также нельзя привести объект к согласованному состоянию.
    // помимо этого при чтении/ изменении объекта невозможна проверка прав доступа пользователя.
    // а еще в типовых операциях чтения и записи стоит реализовать отдельные методы (см rotate и isFitToArea),
    // чтобы уменьшить вероятность ошибки при использовании из нескольких мест.
    public int length;
    public int width;

    public Rectangle(Authorization authorization) {
        if (!(authorization.hasPermission("create") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }
        this.authorization = authorization;
    }

    public void rotate() {
        if (!(authorization.hasPermission("rotate") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }

        length = length + width;
        width = length - width;
        length = length - width;
    }

    public void setLength(int targetLength) {
        if (!(authorization.hasPermission("change") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }

        if (targetLength <= 0) {
            throw new IllegalArgumentException("Set positive value");
        }

        length = targetLength;
    }

    public void setWidth(int targetWidth) {
        if (!(authorization.hasPermission("change") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }

        if (targetWidth <= 0) {
            throw new IllegalArgumentException("Set positive value");
        }

        width = targetWidth;
    }

    public boolean isFitToArea(int dx1, int dy1, int dx2, int dy2) {
        if (!(authorization.hasPermission("change") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }

        return dx2 - dx1 >= length && dy2 - dy1 >= width;
    }
}
