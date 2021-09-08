package ru.geekbrains.service;

import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> findAll();


    Optional<Brand> findById(Long id);

    void save(Brand brand);

    void deleteById(Long id);
}
