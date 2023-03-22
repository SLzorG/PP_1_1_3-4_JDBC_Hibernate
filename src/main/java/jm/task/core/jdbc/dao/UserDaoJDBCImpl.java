package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection con;

    public UserDaoJDBCImpl() {
        con = new Util().getConnection();

    }


    public void createUsersTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("CREATE TABLE new_schema.`user` (\n" +
                    "\tid INT auto_increment NOT NULL,\n" +
                    "\tname varchar(100) NULL,\n" +
                    "\tlastName varchar(100) NULL,\n" +
                    "\tage INT NULL,\n" +
                    "\tCONSTRAINT user_pk PRIMARY KEY (id)\n" +
                    ")\n" +
                    "ENGINE=InnoDB\n" +
                    "DEFAULT CHARSET=utf8mb3\n" +
                    "COLLATE=utf8mb3_general_ci;\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void dropUsersTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DROP TABLE user");

        } catch (SQLSyntaxErrorException e) {

            System.out.println("Такая таблица уже удалена");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastName, age) VALUES(?, ?, ?)";
        System.out.println("User с именем –" + name + " добавлен в базу данных ");
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void removeUserById(long id) {
        String sql = "DELETE FROM user WHERE id =?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        ResultSet rs;
        String query = "select * from user";
        try (Statement stmt = con.createStatement()) {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user1 = new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4));
                user1.setId(rs.getLong(1));
                list.add(user1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DELETE FROM user WHERE id ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}


