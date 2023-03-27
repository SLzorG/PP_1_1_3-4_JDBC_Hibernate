package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }


    public void createUsersTable() {
        Connection con = Util.getConnection();
        try {
            con.setAutoCommit(false);
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
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }
    }


    public void dropUsersTable() {
        Connection con = Util.getConnection();
        try {
            con.setAutoCommit(false);

            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DROP TABLE user");

            } catch (SQLSyntaxErrorException e) {

                System.out.println("Такая таблица уже удалена");
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        Connection con = Util.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "INSERT INTO user (name, lastName, age) VALUES(?, ?, ?)";
            System.out.println("User с именем –" + name + " добавлен в базу данных ");
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, lastName);
                pstmt.setByte(3, age);
                pstmt.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }
    }


    public void removeUserById(long id) {
        Connection con = Util.getConnection();
        try {
            con.setAutoCommit(false);
            String sql = "DELETE FROM user WHERE id =?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }

    }

    public List<User> getAllUsers() {
        Connection con = Util.getConnection();
        List<User> list = new ArrayList<>();
        try {
            con.setAutoCommit(false);

            ResultSet rs;
            String query = "select * from user";
            try (Statement stmt = con.createStatement()) {
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    User user1 = new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4));
                    user1.setId(rs.getLong(1));
                    list.add(user1);
                }
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection con = Util.getConnection();
        try {
            con.setAutoCommit(false);
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("DELETE FROM user WHERE id ");
                con.commit();
            } catch (Exception e) {
                con.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Util.closeConnection();
        }

    }

}


