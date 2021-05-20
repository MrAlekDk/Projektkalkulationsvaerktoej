package com.example.webapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubTask implements Comparable<SubTask> {

    private int subTaskID;
    private String name;
    private String desc;
    private int taskID;
    private int workerID;
    private int duration;
    private Date start;
    private Date deadline;


    public SubTask(String name, String desc, int workerID, int taskID, String start,int duration, String deadline) {
        this.name = name;
        this.desc = desc;
        this.taskID = taskID;
        this.workerID = workerID;
        this.duration = duration;
        this.start = convertStringToDate(start);
        this.deadline = convertStringToDate(deadline);
    }
    public SubTask(int subTaskID,String name, String desc, int taskID, int workerID, Date start,int duration, Date deadline) {
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

    public Date convertStringToDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
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

    @Override
    public int compareTo(SubTask subtask) {

        if(this.start.before(subtask.getStart())){
            return 1;
        }else if(this.start.after(subtask.getStart())){
            return -1;
        }else{
            return 0;
        }
    }
}
