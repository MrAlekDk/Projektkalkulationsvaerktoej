package com.example.webapp.repository;

import com.example.webapp.models.Worker;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class WorkerRep {
    private String url = "jdbc:mysql://den1.mysql6.gear.host:3306/projectcalcdb";
    private String user = "projectcalcdb";
    private String password = "Br1499!_QzIc";
    private ArrayList<Worker> workers;

    public WorkerRep() {
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

    public void getPosition(String position, int hourlyRate){

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("SELECT Worker (Position,HourlyRate) WHERE Position=? & HourlyRate=?");
            ResultSet rs = pstmt.executeQuery();

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Worker> getAllWorkers() {
        ArrayList<Worker> allWorkers = new ArrayList<Worker>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Worker");

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Worker tmp = new Worker(
                        rs.getInt(1),
                        rs.getString(2)
                );
                allWorkers.add(tmp);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allWorkers;

    }

}