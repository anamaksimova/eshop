package ru.geekbrains.persist;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.persist.model.Product;

public class ProductSpecification {
//    public static Specification<Product> namePrefix(String prefix) {
//        return (root, query, builder) -> builder.like(root.get("name"), prefix + "%");
//    }
//
//    public static Specification<Product> minPrice(Float minPrice) {
//        return (root, query, builder) -> builder.ge(root.get("price"), minPrice);
//    }
//
//    public static Specification<Product> maxPrice(Float maxPrice) {
//        return (root, query, builder) -> builder.le(root.get("price"), maxPrice);
//    }
//    public static Specification<Product> categorynamePrefix(String prefixcat) {
//        return (root, query, builder) -> builder.like(root.get("categoryname"), prefixcat + "%");
//    }
    public static Specification<Product> byCategory(long categoryId) {
    return (root, query, builder) -> builder.equal(root.get("category").get("id"), categoryId);
}

    public static Specification<Product> byName(String pattern) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + pattern + "%");
    }
}
