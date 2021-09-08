package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.ProductDto;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.persist.model.Product;

import java.util.Optional;

public interface ProductService {
//    List<Product> findAll();

    Page<Product> findWithFilter(ProductListParams productListParams);
Page<ProductDto> findAll(Integer page, Integer size, String sortField);
    Optional<ProductDto> findById(Long id);

    void save(ProductDto product);

    void deleteById(Long id);
}
