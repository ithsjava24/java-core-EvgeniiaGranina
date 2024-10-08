package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
    private final List<ProductRecord> products = new ArrayList<>();
    private final String name;
    private final static Map<String, Warehouse> instances = new HashMap<>();
    private final Set<UUID> changedProducts = new HashSet<>();

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

    public List<ProductRecord> getProducts() {
        return List.copyOf(products);
    }

    public Object addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");

        if (category == null)
            throw new IllegalArgumentException("Category cannot be null");

        if (id == null) id = UUID.randomUUID();

        if (price == null) price = BigDecimal.ZERO;
        ProductRecord product = new ProductRecord(id, name, category, price);
        products.add(product);
        return product;
    }

    public Optional<ProductRecord> getProductById(UUID id) {

        products.stream()
                .filter(product -> product.uuid().equals(id))
                .toList();
        if (products.isEmpty()) return Optional.empty();
        return Optional.of(products.get(0));
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }

    public List<ProductRecord> getChangedProducts() {
        return products.stream()
                .filter(product -> changedProducts.contains(product.uuid()))
                .collect(Collectors.toList());
    }
}
