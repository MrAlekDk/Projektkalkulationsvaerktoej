package com.example.webapp.repository;
//@Author Alexander J.P.M Sørensen
//@Author Markus Nørtrander Lauge Jakobsen
import com.example.webapp.models.Task;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class TaskRep {
    private String url = "jdbc:mysql://den1.mysql6.gear.host:3306/projectcalcdb";
    private String user = "projectcalcdb";
    private String password = "Br1499!_QzIc";

    public TaskRep(){
        Properties prop = new Properties();
        try{
            prop.load(new FileInputStream("src/main/resources/application.properties"));
            this.url = prop.getProperty("url");
            this.user = prop.getProperty("user");
            this.password = prop.getProperty("password");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addTask(Task newTask){
        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO task (TaskName, TaskNarrative,ProjectID,StartDate, Duration,Deadline) VALUES (?,?,?,?,?,?)");

            pstmt.setString(1, newTask.getName());
            pstmt.setString(2, newTask.getDesc());
            pstmt.setInt(3, newTask.getProjectID());


            java.util.Date utilStartDate1 = newTask.getStartDate();
            java.sql.Date sqlDeadline1 = new java.sql.Date(utilStartDate1.getTime());
            pstmt.setDate(4,sqlDeadline1);

            pstmt.setInt(5, newTask.getNrOfHours());

            java.util.Date utilStartDate2 = newTask.getTaskDeadline();
            java.sql.Date sqlDeadline2 = new java.sql.Date(utilStartDate2.getTime());
            pstmt.setDate(6, sqlDeadline2);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> getAllTasks(int projectID) {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url,user,password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM task WHERE ProjectID=?");
            stmt.setInt(1,projectID);

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Task tmp = new Task(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getDate(5),
                        rs.getInt(6),
                        rs.getDate(7)
                );
                allTasks.add(tmp);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allTasks;
    }

}
