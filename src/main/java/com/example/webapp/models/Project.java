package com.example.webapp.models;

import java.util.ArrayList;
import java.util.Date;

public class Project {
    private String name;
    private String desc;
    private Date deadline;
    private ArrayList<Worker> participants;
    private int nrOfParticipants;
    private int nrOfHours;
    private ArrayList<Task> Tasks;

    public Project(String name, String desc, Date deadline, ArrayList<Worker> participants, int nrOfParticipants, int nrOfHours, ArrayList<Task> tasks){
        this.name = name;
        this.desc = desc;
        this.deadline = deadline;
        this.participants = participants;
        this.nrOfParticipants = nrOfParticipants;
        this.nrOfHours = nrOfHours;
        this.Tasks = tasks;
    }

    public Project(String name, String desc, Date deadline) {
        this.name = name;
        this.desc = desc;
        this.deadline = deadline;
    }
    // ----------------------------|| Getters ||---------------------------- //

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Date getDeadline() {
        return deadline;
    }

    public ArrayList<Worker> getParticipants() {
        return participants;
    }

    public int getNrOfParticipants() {
        return nrOfParticipants;
    }

    public int getNrOfHours() {
        return nrOfHours;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setParticipants(ArrayList<Worker> participants) {
        this.participants = participants;
    }

    public void setNrOfParticipants(int nrOfParticipants) {
        this.nrOfParticipants = nrOfParticipants;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }

    public void setTasks(ArrayList<Task> tasks) {
        Tasks = tasks;
    }
}