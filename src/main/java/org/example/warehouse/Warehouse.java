package org.example.warehouse;

public class Warehouse {
    private String name;
    private Warehouse(String name) {
        this.name = name;
    }
    public static Warehouse getInstance() {
        return new Warehouse("Default Warehouse");
    }
    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }
}
