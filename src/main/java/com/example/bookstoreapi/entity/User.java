package com.example.bookstoreapi.entity;

public final class User {

    private String UID; // cannot re-set
    private String fullname;
    private String phoneNumber; // cannot re-set
    private String address;
    private String imgUrl;

    public User() {
    }

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
