package com.example.webapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Cache {

    Map<Integer, Project> map = new HashMap<Integer, Project>();
    Map<Integer, ArrayList<Task>> test = new HashMap<Integer, ArrayList<Task>>();
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

    public void delete(int key){
        map.remove(key);
    }

    public void setTTL(int key, long time) {
        TimeUnit timeUnit = null;
        timeUnit.toMinutes(time);
        map.remove(key);
    }

}
