package com.example.webapp.models;

import java.util.Date;

public class SubTask {

    private String name;
    private String desc;
    private Worker participant;
    private int duration;
    private Date start;
    private Date end;

    public SubTask(String name, String desc, Worker participant, int duration, Date start, Date end) {
        this.name = name;
        this.desc = desc;
        this.participant = participant;
        this.duration = duration;
        this.start = start;
        this.end = end;
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

    public Worker getParticipant() {
        return participant;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
