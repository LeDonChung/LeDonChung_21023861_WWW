package vn.edu.iuh.fit.donchung.frontend.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.donchung.frontend.models.Account;

import java.io.IOException;

@WebServlet(name = "AccountController", urlPatterns = "/account")
public class AccountController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            account = new Account();
            account.setLogin(true);
            account.setFullName("Le Don Chung");
            session.setAttribute("account", account);
        }

        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
