package oop.y2022.encapsulation.geometry;

import oop.y2022.encapsulation.geometry.authorization.Authorization;
import oop.y2022.encapsulation.geometry.authorization.AuthorizationException;

public class Square extends Rectangle {

    public Square(Authorization authorization) {
        super(authorization);
    }

    @Override
    public void rotate() {
        if (!(authorization.hasPermission("rotate") || authorization.hasPermission(Authorization.ANY_PERMISSION))) {
            throw new AuthorizationException();
        }

        // оптимизируем операцию поворота для случая квадрата
    }

    @Override
    public void setLength(int targetLength) {
        // сохраняем инварианты длин сторон квадрата
        super.setLength(targetLength);
        super.setWidth(targetLength);
    }

    @Override
    public void setWidth(int targetWidth) {
        // сохраняем инварианты длин сторон квадрата
        super.setLength(targetWidth);
        super.setWidth(targetWidth);
    }
}
