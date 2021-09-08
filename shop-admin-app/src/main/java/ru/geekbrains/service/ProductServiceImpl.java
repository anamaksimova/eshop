package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controller.*;
import ru.geekbrains.persist.BrandRepository;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.model.Category;
import ru.geekbrains.persist.model.Picture;
import ru.geekbrains.persist.model.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductSpecifications;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final PictureService pictureService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              BrandRepository brandRepository, PictureService pictureService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.pictureService = pictureService;
    }

    @Override
    public Page<ProductDto> findAll(Integer page, Integer size, String sortField) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by(sortField)))
                .map(product -> new ProductDto(product.getId(),
                        product.getName(),
                        product.getPrice(),
                        new CategoryDto(product.getCategory().getId(), product.getCategory().getName()),
                         new BrandDto(product.getBrand().getId(), product.getBrand().getName())));
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
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductDto(product.getId(),
                        product.getName(),
                        product.getPrice(),
                        new CategoryDto(product.getCategory().getId(), product.getCategory().getName()),
        new BrandDto(product.getBrand().getId(), product.getBrand().getName())));
    }

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        Product product = (productDto.getId() != null) ? productRepository.findById(productDto.getId())
                .orElseThrow(() -> new NotFoundException("")) : new Product();
        Category category = categoryRepository.findById(productDto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDto.getName());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());


        if (productDto.getNewPictures() != null) {
            for (MultipartFile newPicture : productDto.getNewPictures()) {
                try {
                    product.getPictures().add(new Picture(null,
                            newPicture.getOriginalFilename(),
                            newPicture.getContentType(),
                            pictureService.createPicture(newPicture.getBytes()),
                            product
                    ));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
