package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controller.dto.CategoryDto;
import ru.geekbrains.controller.dto.ProductDto;
import ru.geekbrains.controller.dto.ProductListParams;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecifications;
import ru.geekbrains.persist.model.Brand;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

//    @Override
//    public Page<ProductDto> findAll(Integer page, Integer size, String sortField) {
//        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
//                .map(product -> new ProductDto(product.getId(),
//                        product.getName(),
//                        product.getPrice(),
//                        new CategoryDto(product.getCategory().getId(), product.getCategory().getName())));
//    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getId(),
                        product.getName(),
                       product.getPrice(),
                        new CategoryDto(product.getCategory().getId(), product.getCategory().getName()),
                        product.getPictures().stream()
                                .map(Picture::getId)
                                .collect(Collectors.toList())));
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
}
