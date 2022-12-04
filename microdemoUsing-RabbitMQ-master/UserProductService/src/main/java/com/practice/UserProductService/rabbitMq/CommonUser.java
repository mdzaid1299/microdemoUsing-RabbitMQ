package com.practice.UserProductService.rabbitMq;


public class CommonUser {

    private int userId;
    private String password;
    private String userName;
    private String email;
    private long mobileNo;

    public CommonUser(int userId, String password, String userName, String email, long mobileNo) {
        this.userId = userId;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public CommonUser() {
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                '}';
    }
}
