package com.example.webapp.models;

public class Worker {
    private String name;
    private String position;

    public Worker(String name, String position){
        this.name = name;
        this.position = position;
    }


    // ----------------------------|| Getters ||---------------------------- //

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    // ----------------------------|| Setters ||---------------------------- //


    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
