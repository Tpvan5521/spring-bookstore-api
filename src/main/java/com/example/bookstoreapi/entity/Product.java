package com.example.bookstoreapi.entity;

import com.example.bookstoreapi.util.Util;

public final class Product {

    private String productSlug;
    private String name;
    private String description;
    private String information;
    private String review;
    private String availability; // "In Stock" or "Out of order"
    private String category;
    private String imgUrl;
    private int price;
    private int rate;
    private int percentSaleOff;

    public Product() {
        super();
    }

    public Product(String productSlug, String name, String description, String information, String review, String availability, String category, String imgUrl, int price, int rate, int percentSaleOff) {
        setName(name);
        this.description = description;
        this.review = review;
        this.availability = availability;
        this.category = category;
        this.information = information;
        this.imgUrl = imgUrl;
        this.price = price;
        this.rate = rate;
        this.percentSaleOff = percentSaleOff;
    }
    
    public String getProductSlug() {
        return productSlug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.productSlug = Util.parseStringToSlug(Util.nonAccentVietnamese(name), 9999, 1000);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getPercentSaleOff() {
        return percentSaleOff;
    }

    public void setPercentSaleOff(int percentSaleOff) {
        this.percentSaleOff = percentSaleOff;
    }

}
