package com.practice.UserProductService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private int userId;
    private List<Product> productList;
    private String userName;
    private String email;
    private long mobileNo;

    public User() {
    }

    public User(int userId, List<Product> productList, String userName, String email, long mobileNo) {
        this.userId = userId;
        this.productList = productList;
        this.userName = userName;
        this.email = email;
        this.mobileNo = mobileNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProductsList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(long mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", productList=" + productList +
                ", userName='" + userName + '\'' +
                ", address='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                '}';
    }
}