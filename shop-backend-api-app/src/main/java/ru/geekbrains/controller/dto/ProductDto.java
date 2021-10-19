package ru.geekbrains.controller.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.util.List;
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class ProductDto {

    private Long id;

    private String name;


    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
    @JsonSubTypes({ @JsonSubTypes.Type(name = "BIG_DECIMAL", value = BigDecimal.class) })
    private BigDecimal price;

    private CategoryDto category;
    private BrandDto brand;

    private List<Long> pictures;
    private Long mainPictureId;
    public ProductDto() {
    }

    public ProductDto(Long id, String name, BigDecimal price, CategoryDto category, BrandDto brand, List<Long> pictures) {
        this.id = id;
        this.name = name;

        this.price = price;
        this.category = category;
        this.brand = brand;
        this.pictures = pictures;
        if (pictures != null && !pictures.isEmpty()) {
            this.mainPictureId = pictures.get(0);
        }
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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public List<Long> getPictures() {
        return pictures;
    }

    public void setPictures(List<Long> pictures) {
        this.pictures = pictures;
    }
    public Long getMainPictureId() {
        return mainPictureId;
    }

    public void setMainPictureId(Long mainPictureId) {
        this.mainPictureId = mainPictureId;
    }
}