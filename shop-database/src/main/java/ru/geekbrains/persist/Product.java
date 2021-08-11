package ru.geekbrains.persist;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@NotBlank
@Column(nullable = false)
    private  String name;
@Min(0L)
@Column(nullable = false)
    private Float price;


    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(){

    }

    public Product(Long id, String name, Float price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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
}
