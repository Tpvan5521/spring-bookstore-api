package com.example.bookstoreapi.entity;

import com.example.bookstoreapi.util.Util;

public final class User {

    private final String UID; // cannot re-set
    private String fullname;
    private final String phoneNumber; // cannot re-set
    private String address;
    private String userSlug;

    public User(String UID, String fullname, String phoneNumber, String address, String userSlug) {
        this.UID = UID;
        setFullname(fullname);
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getUID() {
        return UID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
        this.userSlug = Util.parseStringToSlug(Util.nonAccentVietnamese(fullname), 999999999, 100000000);
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

    public String getUserId() {
        return userSlug;
    }

    public void setUserId(String userSlug) {
        this.userSlug = userSlug;
    }

}
