package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.controller.BrandDto;
import ru.geekbrains.controller.CategoryDto;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDto> findAll();

    Page<BrandDto> findAll(Integer page, Integer size, String sortField);
    Optional<BrandDto> findById(Long id);

    void save(BrandDto brand);

    void deleteById(Long id);
}
