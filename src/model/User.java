package model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String citizen;
    private Set<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.roles = new HashSet<>();
        this.roles.add(Role.ROLE_ADMIN);
    }

    public User(String username, String password, String fullName, String email, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = new HashSet<>();
        this.roles.add(Role.ROLE_USER);
    }

    public User(String username, String password, String fullName, String email, String phoneNumber, String citizen) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.citizen = citizen;
        this.roles = new HashSet<>();
        this.roles.add(Role.ROLE_EMPLOYEE);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCitizen() {
        return citizen;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}
