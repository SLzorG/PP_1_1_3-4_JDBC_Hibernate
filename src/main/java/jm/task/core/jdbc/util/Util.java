package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "SQLvosemVosmerak888";

    private static Connection con;
    public static Connection getConnection() {

        try {
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.out.println("Ошибка соединения с БД");
        }
        return con;
    }

    public static void closeConnection(){
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия коннекшена");
            }
        }
    }
}

