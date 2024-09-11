package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Account;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.RoleService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControlServlet", urlPatterns = "/control")
public class ControlServlet extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private RoleService roleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equalsIgnoreCase("login")) {
            String accountId = req.getParameter("accountId");
            String password = req.getParameter("password");
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountId(accountId);
            accountDto.setPassword(password);

            AccountDto account = accountService.login(accountDto);
            if (account != null) {
                HttpSession session = req.getSession();
                session.setAttribute("account", account);
                if (account.getGrantAccesses().stream().anyMatch(grantAccessDto -> grantAccessDto.getRole().getRoleId().equalsIgnoreCase("admin"))) {
                    // list users
                    List<AccountDto> accounts = accountService.getAllAccount();
                    // list role
                    List<RoleDto> roles = roleService.getAll();

                    req.setAttribute("accounts", accounts);
                    req.setAttribute("roles", roles);
                     req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);
                } else {
                    req.getRequestDispatcher("views/user.jsp").forward(req, resp);
                }
            } else {
                req.setAttribute("error", "Login failed");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }

        } else if(action.equalsIgnoreCase("logout")) {
            HttpSession session = req.getSession();
            AccountDto account = (AccountDto) session.getAttribute("account");
            if(account == null) {
                req.setAttribute("error", "Please login");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Logout successfully");
                accountService.logout(account);
                session.removeAttribute("account");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }

    }
}
