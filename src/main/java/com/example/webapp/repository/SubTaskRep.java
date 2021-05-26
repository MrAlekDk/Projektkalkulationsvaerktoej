package com.example.webapp.repository;

import com.example.webapp.models.SubTask;
import com.example.webapp.models.Worker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class SubTaskRep {

    private String url = "jdbc:mysql://den1.mysql6.gear.host:3306/projectcalcdb";
    private String user = "projectcalcdb";
    private String password = "Br1499!_QzIc";
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

    public void addSubTask(SubTask newSubTask){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO subtask (SubtaskName, SubtaskNarrative, TaskID, WorkerID, StartDate, Duration, SubtaskDeadline) VALUES (?,?,?,?,?,?,?)");

            pstmt.setString(1, newSubTask.getName());
            pstmt.setString(2, newSubTask.getDesc());
            pstmt.setInt(3, newSubTask.getTaskID());
            pstmt.setInt(4, newSubTask.getWorkerID());


            java.util.Date utilStartDate1 = newSubTask.getStart();
            java.sql.Date sqlDeadline1 = new java.sql.Date(utilStartDate1.getTime());
            pstmt.setDate(5, sqlDeadline1);

            pstmt.setInt(6, newSubTask.getDuration());

            java.util.Date utilStartDate2 = newSubTask.getDeadline();
            java.sql.Date sqlDeadline2 = new java.sql.Date(utilStartDate2.getTime());
            pstmt.setDate(7,sqlDeadline2);
            pstmt.executeUpdate();

            System.out.println("Good job adding a subtask!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<SubTask> getAllSubTasks() {
        ArrayList<SubTask> allSubTasks = new ArrayList<SubTask>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM subtask");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                SubTask tmp = new SubTask(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getInt(7),
                        rs.getDate(8)
                );
                allSubTasks.add(tmp);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allSubTasks;

    }

}
