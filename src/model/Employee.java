package model;

import java.util.HashSet;
import java.util.Set;

public class Employee extends Person{
    private String fullName;
    private String email;
    private String phoneNumber;
    private String citizen;
    private Set<Role> roles;

    public Employee(String username, String password, String fullName, String email, String phoneNumber, String citizen) {
        super(username, password);
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.citizen = citizen;
        this.roles = new HashSet<>();
        this.roles.add(Role.ROLE_EMPLOYEE);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCitizen() {
        return citizen;
    }

    public void setCitizen(String citizen) {
        this.citizen = citizen;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
    }
}
