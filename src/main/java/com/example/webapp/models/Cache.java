package com.example.webapp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Cache {

    Map<Integer, Project> map = new HashMap<Integer, Project>();
    Map<Integer, ArrayList<Task>> test = new HashMap<Integer, ArrayList<Task>>();
    ArrayList<Project> allProjects = new ArrayList<>();

    public Project get(int key){
        return map.get(key);
    }
    public ArrayList<Task> getList (int key){return test.get(key);}

    public Project set(int key, Project value){
        return map.put(key, value);
    }
    public ArrayList<Task> setList(int key, ArrayList<Task> list){return test.put(key, list);}
    public Boolean has(int key){
        return map.containsKey(key);
    }

    public void setProjects(ArrayList<Project> allProjects){
        this.allProjects = allProjects;
    }

    public void delete(int key){
        map.remove(key);
    }

    public void setTTL(int key, long time) {
        TimeUnit timeUnit = null;
        timeUnit.toMinutes(time);
        map.remove(key);
    }

    public boolean hasProjects() {
        if (allProjects.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList<Project> getAllProjects() {
        return this.allProjects;
    }
}
