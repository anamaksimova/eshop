package ru.geekbrains.persist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "categoryname", unique = true, nullable = false)
    private String categoryname;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(){

    }

    public Category(Long id, String categoryname) {
        this.id = id;
        this.categoryname = categoryname;

    }

    public Long getId() {
        return id;
    }

    public String getCategoryname() {
        return categoryname;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
