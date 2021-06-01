package com.example.webapp.repository;
//@Author Mette Marie H. Winther-Sørensen


import com.example.webapp.models.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class UserRep {
    private String url = "jdbc:mysql://den1.mysql6.gear.host:3306/projectcalcdb";
    private String user = "projectcalcdb";
    private String password = "Br1499!_QzIc";

    public UserRep() {
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

    public User checkUser(String username, String password1) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user,password);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString(2).equals(username)) {
                    if (rs.getString(3).equals(password1)) {
                        User user = new User(
                                rs.getString(2),
                                rs.getString(3));
                        return user;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong with check user");
            System.out.println(e.getMessage());
        }
        return null;
    }

}
