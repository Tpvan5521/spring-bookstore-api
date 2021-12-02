package com.example.bookstoreapi.entity;

public final class User {

    private final String UID; // cannot re-set
    private String fullname;
    private final String phoneNumber; // cannot re-set
    private String address;
    private String imgUrl;

    public User(String UID, String fullname, String phoneNumber, String address, String imgUrl) {
        this.UID = UID;
        this.fullname = fullname;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.imgUrl = imgUrl;
    }

    public String getUID() {
        return UID;
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

}
