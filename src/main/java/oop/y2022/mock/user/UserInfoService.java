package oop.y2022.mock.user;

import java.util.Optional;
import java.util.UUID;

/** Сервис данных пользователя. Предоставляет данные пользователя из общей базы, если такой пользователь есть. */
public class UserInfoService implements IUserInfoService {

    /**
     * Метод содержит много логики, условий, запросов к сторонним сервисам.
     * Ниже - схематичная реализация.
     */
    @Override
    public Optional<UserInfo> findUserInfo(UUID userId) {
        if (sum(1, 2) == 3) {
            System.out.println("1 + 2 is 3");

        } else  if (sum(2, 2) == 3) {
            System.out.println("1 + 2 is 3");
            return Optional.empty();
        }

        if (sum(2, 2) != 3) {
            System.out.println("2 and 2 is four");
        }

        if (sum(10, 2) == 3) {
            System.out.println("10 + 2 is 3");

        } else  if (sum(12, 2) == 3) {
            System.out.println("12 + 2 is 3");
            return Optional.empty();
        }

        if (sum(12, 2) != 3) {
            System.out.println("2222 and 2 is four");
        }

        if (sum(-1, -2) == 3) {
            System.out.println("Error");
            throw new RuntimeException("Error on calc -1 + -2");
        }

        return Optional.of(new UserInfo("Иван Иванов"));
    }

    private int sum(int a, int b) {
        return a + b;
    }
}
