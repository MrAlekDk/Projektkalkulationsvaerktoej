package com.example.webapp.repository;

import com.example.webapp.models.Worker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class SubTaskRep {

    private String url;
    private String user;
    private String password;
    private ArrayList<Worker> workers;

    public SubTaskRep() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSubTask(int Subtask_ID, String title, String desc, int task_ID, Date subtaskStart, int duration, Date subtaskEnd){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SubTask (SubtaskID, Title, Desc, Task_ID, SubtaskStart, Duration, SubtaskEnd) VALUES (?,?,?,?,?,?,?)");

            pstmt.setInt(1, Subtask_ID);
            pstmt.setString(2, title);
            pstmt.setString(3, desc);
            pstmt.setInt(4, task_ID);
            pstmt.setDate(5, (java.sql.Date) subtaskStart);
            pstmt.setInt(6, duration);
            pstmt.setDate(7,(java.sql.Date) subtaskEnd);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
