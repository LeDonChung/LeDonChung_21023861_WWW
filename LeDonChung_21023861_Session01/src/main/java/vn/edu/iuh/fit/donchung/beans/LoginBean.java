package vn.edu.iuh.fit.donchung.beans;

import vn.edu.iuh.fit.donchung.POJO.User;

import java.sql.*;

public class LoginBean {
    private UserDAO userDAO;
    public LoginBean() {
        userDAO = new UserDAO();
    }
    public boolean login(User user) throws SQLException {
        User userLogin = userDAO.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userLogin != null;
    }


}
