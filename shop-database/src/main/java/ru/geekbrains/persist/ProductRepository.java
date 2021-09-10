package ru.geekbrains.persist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import ru.geekbrains.persist.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product,Long>, JpaSpecificationExecutor<Product> {
//    List<Product> findByNameStartsWith(String prefix);
//    @Query(value = "select p from Product p left join fetch p.category",
//            countQuery = "select count(p) from Product p")
//    Page<Product> findAll(Pageable var2);
//    @Query("select p " +
//            "from Product p " +
//            "where ( p.name like CONCAT(:prefix, '%') or :prefix is null ) and " +
//            "( p.price >= :minPrice or :minPrice is null ) and " +
//            "( p.price <= :maxPrice or :maxPrice is null )")
//    List<Product> filterProducts(@Param("prefix") String prefix,
//                           @Param("minPrice") Float minPrice,
//                           @Param("maxPrice") Float maxPrice);
//

    Page<Product> findAll(@Nullable Specification<Product> spec, Pageable pageable);
    List<Product> findProductByNameLike(String name);
    List<Product> findByPriceBetween(Float min, Float max);
}
