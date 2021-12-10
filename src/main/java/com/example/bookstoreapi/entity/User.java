package com.example.bookstoreapi.entity;

public final class User {

    private String UID; // cannot re-set
    private String fullname;
    private String phoneNumber; // cannot re-set
    private String address;
    private String imgUrl;
    private String email;

    public User() {
    }

    public User(String UID, String fullname, String phoneNumber, String address, String imgUrl, String email) {
        this.UID = UID;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.imgUrl = imgUrl;
        this.email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
