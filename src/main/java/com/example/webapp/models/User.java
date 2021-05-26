package com.example.webapp.models;


public class User {

    private int userID;
    private String mail;
    private String password;

    public User(int userID, String mail, String password){
        this.userID = userID;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    // ----------------------------|| Getters ||---------------------------- //

    public int getUserID() {
        return userID;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
