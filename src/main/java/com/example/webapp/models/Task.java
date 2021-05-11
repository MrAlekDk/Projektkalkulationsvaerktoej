package com.example.webapp.models;

import java.util.ArrayList;
import java.util.Date;

public class Task {
    private String name;
    private String desc;
    private Worker participant;
    private Date startDate;
    private int nrOfHours;
    private Date taskDeadline;
    private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();

    public Task (String name, String desc, Worker participant, Date startDate, int nrOfHours, Date taskDeadline){
        this.name = name;
        this.desc = desc;
        this.participant = participant;
        this.startDate = startDate;
        this.nrOfHours = nrOfHours;
        this.taskDeadline = taskDeadline;
    }

    public Task(String taskName, String taskDesc) {
        this.name=taskName;
        this.desc=taskDesc;
    }

    // ----------------------------|| Getters ||---------------------------- //

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Worker getParticipant() {
        return participant;
    }

    public Date getStartDate() {
        return startDate;
    }

    public int getNrOfHours() {
        return nrOfHours;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setParticipant(Worker participant) {
        this.participant = participant;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void addSubtask(SubTask newSubtask){
        this.subtasks.add(newSubtask);
    }

    public ArrayList<SubTask> getSubtasks(){
        return this.subtasks;
    }
}
