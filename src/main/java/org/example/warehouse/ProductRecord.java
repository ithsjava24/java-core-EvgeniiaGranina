package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


public record ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord that)) return false;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "ProductRecord[" +
                "uuid=" + uuid + ", " +
                "name=" + name + ", " +
                "category=" + category + ", " +
                "price=" + price + ']';
    }


}
