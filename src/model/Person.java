package model;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private String username;
    private String password;
    private final Set<Role> roles;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add(Role.ROLE_ADMIN);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
