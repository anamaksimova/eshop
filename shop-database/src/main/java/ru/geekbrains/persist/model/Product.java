package ru.geekbrains.persist.model;

import ru.geekbrains.persist.model.Category;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedEntityGraph(
        name = "product-with-category",
        attributeNodes = {
                @NamedAttributeNode("category"),
                @NamedAttributeNode("pictures")
        }
)
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//@NotBlank
@Column(nullable = false)
    private  String name;
//@Min(0L)
@Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Picture> pictures = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    public Product(){

    }

    public Product(Long id, String name, BigDecimal price, Category category, Brand brand) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category  getCategory() {
        return  category;
    }

    public void setCategory(Category category) {
        this.category = category;}
    public Brand  getBrand() {
        return  brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;}
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
