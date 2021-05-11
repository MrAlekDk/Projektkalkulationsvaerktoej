package com.example.webapp.models;

public class Worker {
    private String position;

    public Worker(String position){
        this.position = position;
    }

    // ----------------------------|| Getters ||---------------------------- //

    public String getPosition() {
        return position;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setPosition(String position) {
        this.position = position;
    }
}
