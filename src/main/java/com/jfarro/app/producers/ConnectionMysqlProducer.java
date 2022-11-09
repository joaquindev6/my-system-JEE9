package com.jfarro.app.producers;

import com.jfarro.app.annotations.ConnectionMySQL;
import com.jfarro.app.annotations.ConnectionPostgreSQL;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysqlProducer {

    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio1?useTimezone=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "farro123";

//    @Produces
//    @RequestScoped
//    @ConnectionMySQL
//    private Connection getConnectioMysql() throws SQLException, ClassNotFoundException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection("URL, USERNAME, PASSWORD");
//    }

    @Produces
    @RequestScoped
    @ConnectionPostgreSQL
    private Connection getConnectioPostgreSQL() throws SQLException, ClassNotFoundException {
        Class.forName("org.posetgresql.Driver");
        return DriverManager.getConnection("postgres://tkdnpnqlgxntpi:4e034f7db37ffe0ee6fdba71d896df34ee150e30972d36c44b8a76b2dc9dff7b@ec2-54-163-34-107.compute-1.amazonaws.com:5432/d924lljh3h2q75");
    }
}
