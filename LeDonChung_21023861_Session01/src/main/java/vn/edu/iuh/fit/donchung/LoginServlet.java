package vn.edu.iuh.fit.donchung;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.donchung.POJO.User;
import vn.edu.iuh.fit.donchung.beans.LoginBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        LoginBean loginBean = new LoginBean();
        boolean isLogin = false;
        try {
            isLogin = loginBean.login(user);
            if(isLogin) {
                out.println("<p>Hello " + username + " login successfully.");
            } else {
                out.println("<p>Login Failed</p>");
            }
        } catch (SQLException e) {
            out.println("<p>Login Failed</p>");
        }
    }
}
