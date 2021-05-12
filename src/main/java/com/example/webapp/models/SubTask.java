package com.example.webapp.models;

import java.util.Date;

public class SubTask {

    private int subTaskID;
    private String name;
    private String desc;
    private int taskID;
    private int workerID;
    private int duration;
    private Date start;
    private Date deadline;


    public SubTask(String name, String desc, int workerID, int taskID, Date start,int duration, Date deadline) {
        this.name = name;
        this.desc = desc;
        this.taskID = taskID;
        this.workerID = workerID;
        this.duration = duration;
        this.start = start;
        this.deadline = deadline;
    }
    public SubTask(int subTaskID,String name, String desc, int workerID, int taskID, Date start,int duration, Date deadline) {
        this.subTaskID = subTaskID;
        this.name = name;
        this.desc = desc;
        this.taskID = taskID;
        this.workerID = workerID;
        this.duration = duration;
        this.start = start;
        this.deadline = deadline;
    }

    public SubTask(String name, String desc){
        this.name = name;
        this.desc = desc;
    }

    // ----------------------------|| Getters ||---------------------------- //

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getWorkerID() {
        return this.workerID;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStart() {
        return start;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public int getTaskID() {
        return this.taskID;
    }

    // ----------------------------|| Setters ||---------------------------- //

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setDeadline(Date newDeadline) {
        this.deadline = newDeadline;
    }

}
