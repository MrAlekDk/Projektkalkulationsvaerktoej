package com.example.webapp.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task {
    private int taskID;
    private String name;
    private String desc;
    private int projectID;
    private Date startDate;
    private int nrOfHours;
    private Date taskDeadline;
    private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();

    public Task (String name, String desc, int projectID, Date startDate, int nrOfHours, Date taskDeadline){
        this.name = name;
        this.desc = desc;
        this.projectID = projectID;
        this.startDate = startDate;
        this.nrOfHours = nrOfHours;
        this.taskDeadline = taskDeadline;
    }

    public Task (int taskID,String name, String desc, int projectID, Date startDate, int nrOfHours, Date taskDeadline){
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

    public Date getStartDate() {
        return startDate;
    }

    public int getNrOfHours() {
        return nrOfHours;
    }

    public Date getTaskDeadline() {
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setNrOfHours(int nrOfHours) {
        this.nrOfHours = nrOfHours;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public void addSubtask(SubTask subTasks){
        this.subtasks.add(subTasks);
    }

}
