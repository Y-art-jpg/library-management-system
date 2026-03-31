package com.library.system;
public class Person {
    protected String id;
    protected String name;
    protected String password;
    protected String role;

    public Person(String id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role + "," + id + "," + name + "," + password;
    }
}