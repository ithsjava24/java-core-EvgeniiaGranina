package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private final List<ProductRecord> products = new ArrayList<>();
    private final String name;
    private final static Map<String, Warehouse> instances = new HashMap<>();
    private Warehouse(String name) {
        this.name = name;
    }
    public static Warehouse getInstance() {
        return new Warehouse("Default Warehouse");
    }
    public static Warehouse getInstance(String name) {
        if (instances.containsKey(name)) {
            return instances.get(name);
        } else {
            Warehouse warehouse = new Warehouse(name);
            instances.put(name, warehouse);
            return warehouse;
        }
    }
    public boolean isEmpty() {
        return name.isEmpty();
    }

    public boolean getProducts() {
        return name.contains("Products");
    }

    public Object addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (category == null) throw new IllegalArgumentException("Category cannot be null");

        if (id == null) id = UUID.randomUUID();

        if (price == null) price = BigDecimal.ZERO;
        ProductRecord product = new ProductRecord(id, name, category, price);
        products.add(product);
        return product;
    }

    public Optional<ProductRecord> getProductById(UUID uuid) {

        return Optional.empty();
    }
}
