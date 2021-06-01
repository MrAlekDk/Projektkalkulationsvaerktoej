package com.example.webapp.models;
//@Author Mette Marie H. Winther-Sørensen
public class Worker {
    private String position;
    private int worker_ID;
    private int hourlyRate;

    public Worker(String position){
        this.position = position;
    }

    public Worker (int worker_ID, String position, int hourlyRate){
        this.worker_ID = worker_ID;
        this.position = position;
        this.hourlyRate = hourlyRate;
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
