package ru.geekbrains.persist;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecifications {
    public static Specification<Product> namePrefix(String prefix) {
        return (root, query, builder) -> builder.like(root.get("name"), prefix + "%");
    }

    public static Specification<Product> minPrice(Float minPrice) {
        return (root, query, builder) -> builder.ge(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(Float maxPrice) {
        return (root, query, builder) -> builder.le(root.get("price"), maxPrice);
    }
    public static Specification<Product> categorynamePrefix(String prefixcat) {
        return (root, query, builder) -> builder.like(root.get("categoryname"), prefixcat + "%");
    }
}
