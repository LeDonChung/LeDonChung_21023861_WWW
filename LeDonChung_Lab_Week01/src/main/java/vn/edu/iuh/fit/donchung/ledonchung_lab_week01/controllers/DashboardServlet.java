package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.datafaker.Faker;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.entities.Role;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.repositories.RoleRepository;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.RoleService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "DashboardServlet", urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private RoleService roleService;
    @Inject
    private RoleRepository roleRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // list users
        List<AccountDto> accounts = accountService.getAllAccount();
        // list role
        List<RoleDto> roles = roleService.getAll();

        req.setAttribute("accounts", accounts);
        req.setAttribute("roles", roles);

        req.getRequestDispatcher("views/dashboard.jsp").forward(req, resp);
    }
}
