package com.example.webapp.models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Cache {

    Map<Integer, Project> map = new HashMap<Integer, Project>();

    public Project get(int key){
        return map.get(key);
    }

    public Project set(int key, Project value){
        return map.put(key, value);
    }

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
