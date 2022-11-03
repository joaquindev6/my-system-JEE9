package com.jfarro.app.producers;

import com.jfarro.app.annotations.ConnectionMySQL;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMysqlProducer {

    private static final String URL = "jdbc:mysql://localhost:3306/ejercicio1?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "farro123";

    @Produces
    @RequestScoped
    @ConnectionMySQL
    private Connection getConnectioMysql() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
