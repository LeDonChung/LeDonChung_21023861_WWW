package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;

import java.io.IOException;

@WebServlet(name = "ControlServlet", urlPatterns = "/control")
public class ControlServlet extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action.equalsIgnoreCase("login")) {
            String accountId = req.getParameter("accountId");
            String password = req.getParameter("password");
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountId(accountId);
            accountDto.setPassword(password);

            AccountDto account = accountService.find(accountDto);
            if(account != null) {

                if(account.getGrantAccesses().stream().anyMatch(grantAccessDto -> grantAccessDto.getRole().getRoleName().equalsIgnoreCase("admin"))) {
                    req.getRequestDispatcher("views/admin.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("views/user.jsp").forward(req, resp);
                }

            } else {
                req.setAttribute("message", "Login failed");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

        }

    }
}
