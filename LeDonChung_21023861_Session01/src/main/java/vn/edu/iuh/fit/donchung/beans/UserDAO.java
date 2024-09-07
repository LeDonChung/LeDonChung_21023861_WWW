package vn.edu.iuh.fit.donchung.beans;

import vn.edu.iuh.fit.donchung.POJO.User;

import java.sql.*;

public class UserDAO {
    private final String URL = "jdbc:mysql://localhost:3306/session01";
    private final String USERNAME = "root";
    private final String PASSWORD = "sapassword";
    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    public User findByUsernameAndPassword(String username, String password) {
        try(var connection = getConnection(URL, USERNAME, PASSWORD)) {
            if(connection != null) {
                String query = "SELECT *FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                User userLogin = null;
                while(resultSet.next()) {
                    String us = resultSet.getString("username");
                    String pwd = resultSet.getString("password");
                    userLogin = new User(us, pwd);
                }
                return userLogin;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
