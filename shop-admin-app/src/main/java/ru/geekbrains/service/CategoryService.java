package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();


    Optional<Category> findById(Long id);

    void save(Category category);

    void deleteById(Long id);
}
