package com.example.bookstoreapi.entity;

public class FavoriteItem {
    private String userId;
    private String productId;
    
    public FavoriteItem(){
    }

    public FavoriteItem(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    
}
