package jm.task.core.jdbc.util;//package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {
    // реализуйте настройку соеденения с БД
//
//    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
//    private static final String USER = "root";
//    private static final String PASSWORD = "SQLvosemVosmerak888";
//
//    private static Connection con;
//
//    public static Connection getConnection() {
//
//        try {
//
//            con = DriverManager.getConnection(URL, USER, PASSWORD);
//
//        } catch (SQLException e) {
//            System.out.println("Ошибка соединения с БД");
//        }
//        return con;
//    }
//
//    public static void closeConnection() {
//        if (con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.out.println("Ошибка закрытия коннекшена");
//            }
//        }
//    }

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/new_schema");
            properties.setProperty("hibernate.connection.username", "root");
            properties.setProperty("hibernate.connection.password", "SQLvosemVosmerak888");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Проблема с созданием SessionFactory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}


