package ru.geekbrains.controller;

import java.math.BigDecimal;

public class ProductListParams {
    private String nameFilter;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer page;

    private Integer size;
    private String categoryNameFilter;
    private String sortField;
//    private String sortDir;
//    public String getSortDir() {
//        return sortDir;
//    }
//
//    public void setSortDir(String sortDir) {
//        this.sortDir = sortDir;
//    }
//    public String getReverseSortDir() {
//
//        return sortDir.equals("asc") ? ("desc") : ("asc");
//    }


    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getCategoryNameFilter() {
        return categoryNameFilter;
    }
    public void setCategoryNameFilter(String categoryNameFilter) {
        this.categoryNameFilter = categoryNameFilter;
    }
}
