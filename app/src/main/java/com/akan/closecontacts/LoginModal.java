package com.akan.closecontacts;

public class LoginModal{
    private String UserName;
    private String PhoneNumber;
    private int id;

    public String getUserName(){
        return UserName;
    }
    public void setUserName(String UserName){
        this.UserName = UserName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public int getId() {
        return id;
    }

    public LoginModal(String UserName, String PhoneNumber){
        this.UserName = UserName;
        this.PhoneNumber = PhoneNumber;
    }
}
