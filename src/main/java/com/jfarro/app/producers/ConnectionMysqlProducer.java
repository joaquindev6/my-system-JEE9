package com.jfarro.app.producers;

import com.jfarro.app.annotations.ConnectionMySQL;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysqlProducer {

    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio1?useTimezone=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "farro123";

    @Produces
    @RequestScoped
    @ConnectionMySQL
    private Connection getConnectioMysql() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

//    @Produces
//    @RequestScoped
//    @ConnectionMySQL
//    private Connection getConnectioPostgreSQL() throws SQLException, ClassNotFoundException {
//        Class.forName("org.postgresql.Driver");
//        return DriverManager.getConnection("jdbc:postgresql://ec2-54-163-34-107.compute-1.amazonaws.com:5432/d924lljh3h2q75", "tkdnpnqlgxntpi", "4e034f7db37ffe0ee6fdba71d896df34ee150e30972d36c44b8a76b2dc9dff7b");
//    }

    public void close(@Disposes @ConnectionMySQL Connection conn) throws SQLException {
        conn.close();
    }
}
