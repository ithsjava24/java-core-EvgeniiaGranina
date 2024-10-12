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
            instances.get(name).products.clear();
            return instances.get(name);
        } else {
            Warehouse warehouse = new Warehouse(name);
            instances.put(name, warehouse);
            return warehouse;
        }
    }
    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        return List.copyOf(products);
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Product name can't be null or empty.");

        if (category == null)
            throw new IllegalArgumentException("Category can't be null.");

        if (id == null) id = UUID.randomUUID();
        final UUID finalID = id;

        if (products.stream().anyMatch(product -> product.uuid().equals(finalID)))
            throw new IllegalArgumentException("Product already exists");

        if (price == null) price = BigDecimal.ZERO;


        ProductRecord product = new ProductRecord(id, name, category, price);
        if (products.contains(product))
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        products.add(product);
        return product;
    }

    public Optional<ProductRecord> getProductById(UUID id) {

        return products.stream()
                .filter(product -> product.uuid().equals(id))
                .findFirst();

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
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        getProductById(id).ifPresentOrElse(product -> {
            products.set(products.indexOf(product),
                            new ProductRecord(product.uuid(), product.name(), product.category(), newPrice));
            changedProducts.add(id);
                }
        , () -> {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
                });

    }
    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream().collect(Collectors.groupingBy(ProductRecord::category));
    }
}
