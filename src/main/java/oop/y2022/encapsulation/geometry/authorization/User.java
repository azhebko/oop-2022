package oop.y2022.encapsulation.geometry.authorization;

import java.util.List;

public class User {

    private final int id;
    private final String name;
    private final List<String> permissions;

    public User(int id, String name, List<String> permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }

    public User(int id, String name, String... permissions) {
        this(id, name, List.of(permissions));
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
