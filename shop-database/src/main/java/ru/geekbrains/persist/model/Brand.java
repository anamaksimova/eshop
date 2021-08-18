package ru.geekbrains.persist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "brandname")
    private String brandname;

    @JsonIgnore
    @OneToMany(mappedBy = "brand")
    private List<Product> products;

    public Brand(){

    }

    public Brand(Long id, String brandname) {
        this.id = id;
        this.brandname = brandname;

    }

    public Long getId() {
        return id;
    }

    public String getBrandname() {
        return brandname;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
