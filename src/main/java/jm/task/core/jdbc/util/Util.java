package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private final String url = "jdbc:mysql://localhost:3306/new_schema";
    private final String user = "root";
    private final String password = "SQLvosemVosmerak888";


    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Ошибка с соединением с БД");
            ;
        }
        return con;

    }
}

