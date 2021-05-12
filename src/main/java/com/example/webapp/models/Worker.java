package com.example.webapp.models;

public class Worker {
    private String position;
    private int worker_ID;

    public Worker(String position){
        this.position = position;
    }

    public Worker (int worker_ID, String position){
        this.worker_ID = worker_ID;
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
