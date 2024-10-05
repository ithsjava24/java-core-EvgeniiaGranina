package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

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
    public boolean isEmpty() {
        return name.isEmpty();
    }

    public boolean getProducts() {
        return name.contains("Products");
    }

    public Object addProduct(UUID uuid, String name, Category category, BigDecimal price) {

        return null;
    }
}
