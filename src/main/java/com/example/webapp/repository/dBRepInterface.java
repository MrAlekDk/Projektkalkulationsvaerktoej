package com.example.webapp.repository;

import java.sql.Connection;

public interface dBRepInterface {

    public Connection getConnection(String url,String user,String password);


}
