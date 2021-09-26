package ru.geekbrains.controller;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class ProductDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private CategoryDto category;
    private BrandDto brand;

    private List<Long> pictures;

    private MultipartFile[] newPictures;
    private Set<CategoryDto> categories;
    private Set<BrandDto> brands;
    public ProductDto() {
    }

    public ProductDto(Long id, String name,  BigDecimal price, CategoryDto category,BrandDto brand,List<Long> pictures) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.pictures = pictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }

    public MultipartFile[] getNewPictures() {
        return newPictures;
    }

    public void setNewPictures(MultipartFile[] newPictures) {
        this.newPictures = newPictures;
    }
    public Set<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryDto> categories) {
        this.categories = categories;
    }
    public Set<BrandDto> getBrands() {
        return brands;
    }

    public void setBrands(Set<BrandDto> brands) {
        this.brands = brands;
    }

}