package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams productListParams) {
        Specification<Product> spec = Specification.where(null);

        if (productListParams.getNameFilter() != null && !productListParams.getNameFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.namePrefix(productListParams.getNameFilter()));
        }
        if (productListParams.getMinPrice() != null) {
            spec = spec.and(ProductSpecifications.minPrice(productListParams.getMinPrice()));
        }
        if (productListParams.getMaxPrice() != null ) {
            spec = spec.and(ProductSpecifications.maxPrice(productListParams.getMaxPrice()));
        }
        if (productListParams.getCategoryNameFilter() != null && !productListParams.getCategoryNameFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.namePrefix(productListParams.getCategoryNameFilter()));
        }

        return productRepository.findAll(spec,
                PageRequest.of(
                        Optional.ofNullable(productListParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(productListParams.getSize()).orElse(3),
                        Sort.by(Optional.ofNullable(productListParams.getSortField())
                                .filter(c -> !c.isBlank())
                                .orElse("id"))));
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
