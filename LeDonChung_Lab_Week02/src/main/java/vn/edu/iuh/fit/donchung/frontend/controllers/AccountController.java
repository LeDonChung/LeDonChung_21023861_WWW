package vn.edu.iuh.fit.donchung.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.donchung.frontend.models.Account;
import vn.edu.iuh.fit.donchung.frontend.models.AccountModel;

import java.io.IOException;

@WebServlet(name = "AccountController", urlPatterns = "/account")
public class AccountController extends HttpServlet {
    @EJB
    private AccountModel accountModel;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if(action.equalsIgnoreCase("login")) {
            String phone = req.getParameter("phone");
            if(phone != null) {

                Account account = accountModel.findByPhone(phone);
                if(account != null) {
                    session.setAttribute("account", account);
                    resp.sendRedirect(req.getContextPath() + "/orders");
                } else {
                    req.setAttribute("message", "Tài khoản không tồn tại");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                }
            }

        } else if(action.equalsIgnoreCase("logout")) {
            session.removeAttribute("account");
            req.setAttribute("message", "Đăng xuất thành công");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
