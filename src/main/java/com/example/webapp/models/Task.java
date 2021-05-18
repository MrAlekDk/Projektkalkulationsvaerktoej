package com.example.webapp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private int taskID;
    private String name;
    private String desc;
    private int projectID;
    private LocalDate startDate;
    private int nrOfHours;
    private LocalDate taskDeadline;
    private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();

    public Task (String name, String desc, int projectID, LocalDate startDate, int nrOfHours, LocalDate taskDeadline){
        this.name = name;
        this.desc = desc;
        this.projectID = projectID;
        this.startDate = startDate;
        this.nrOfHours = nrOfHours;
        this.taskDeadline = taskDeadline;
    }

    public Task (int taskID,String name, String desc, int projectID, LocalDate startDate, int nrOfHours, LocalDate taskDeadline){
        this.taskID=taskID;
        this.name = name;
        this.desc = desc;
        this.projectID = projectID;
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

    public int getTaskID(){
        return this.taskID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getNrOfHours() {
        return nrOfHours;
    }

    public LocalDate getTaskDeadline() {
        return taskDeadline;
    }

    public int getProjectID() {
        return this.projectID;
    }

    public ArrayList<SubTask> getSubtasks(){
        return this.subtasks;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }

    public void setTaskDeadline(LocalDate taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void addSubtask(SubTask subTasks){
        this.subtasks.add(subTasks);
    }

}
