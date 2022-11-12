package oop.y2022.encapsulation.geometry.authorization;

/**
 * Утильный класс проверки прав пользователей с некоторыми операциями.
 * Типичный сценарий использования:
 * * Вызываем login при поступлении запроса.
 * * Вызываем необходимые для запроса действия, проверяя необходимые права.
 * * Вызываем logout по окончании запроса.
 * */
public class Authorization {

    public static final String ANY_PERMISSION = "any";

    // деталь реализации - ThreadLocal-ная переменная,
    // можно переиспользовать один и тот же объект Authorization в разных потоках
    private final ThreadLocal<User> users = new ThreadLocal<>();

    public void login(User user) {
        users.set(user);
    }

    public void logout() {
        users.set(null);
    }

    public boolean hasPermission(String permission) {
        User user = users.get();
        return user != null && user.getPermissions().contains(permission);
    }

}
