package ru.geekbrains.persist.model;

import ru.geekbrains.persist.model.Category;

import javax.persistence.*;


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
    private Float price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    public Product(){

    }

    public Product(Long id, String name, Float price, Category category, Brand brand) {
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
    public Float getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPrice(Float price) {
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
}