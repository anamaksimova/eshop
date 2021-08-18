package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}