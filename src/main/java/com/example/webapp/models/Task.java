package com.example.webapp.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Task implements Comparable<Task> {
    private int taskID;
    private String name;
    private String desc;
    private int projectID;
    private Date startDate;
    private int nrOfHours;
    private Date taskDeadline;
    private ArrayList<SubTask> subtasks = new ArrayList<SubTask>();

    public Task (String name, String desc, int projectID, String startDate, int nrOfHours, String taskDeadline){
        this.name = name;
        this.desc = desc;
        this.projectID = projectID;
        this.startDate = convertStringToDate(startDate);
        this.nrOfHours = nrOfHours;
        this.taskDeadline = convertStringToDate(taskDeadline);
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

    @Override
    public int compareTo(Task task) {

        if(this.startDate.before(task.getStartDate())){
            return 1;
        }else if(this.startDate.after(task.getStartDate())){
            return -1;
        }else{
            return 0;
        }
    }

    //It returns the value 0 if the argument Date is equal to this Date.
    //It returns a value less than 0 if this Date is before the Date argument.
    //It returns a value greater than 0 if this Date is after the Date argument.
}
