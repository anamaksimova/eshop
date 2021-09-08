package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.CategoryDto;
import ru.geekbrains.persist.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> findAll();

    Page<CategoryDto> findAll(Integer page, Integer size, String sortField);
    Optional<CategoryDto> findById(Long id);

    void save(CategoryDto category);

    void deleteById(Long id);
}
