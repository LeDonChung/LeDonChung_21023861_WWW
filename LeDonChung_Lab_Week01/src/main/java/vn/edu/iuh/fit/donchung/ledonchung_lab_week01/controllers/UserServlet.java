package vn.edu.iuh.fit.donchung.ledonchung_lab_week01.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.GrantAccessDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.RoleDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.AccountService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week01.services.RoleService;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    @Inject
    private AccountService accountService;
    @Inject
    private RoleService roleService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        AccountDto accountDto = new AccountDto();
        List<RoleDto> roles = roleService.getAll();
        if(action.equalsIgnoreCase("edit")) {
            String accountId = req.getParameter("accountId");
            accountDto = accountService.getById(accountId);
        }
        req.setAttribute("account", accountDto);
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("views/edit-user.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        // Lấy danh sách các role IDs được chọn
        String[] roles = req.getParameterValues("roles");
        Set<String> roleSet = new HashSet<>(Arrays.asList(roles));

        Set<GrantAccessDto> grantAccess = roleSet.stream().map(roleId -> {
            GrantAccessDto grantAccessDto = new GrantAccessDto();
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleId(roleId);
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountId(null);

            grantAccessDto.setAccount(accountDto);
            grantAccessDto.setRole(roleDto);
            return grantAccessDto;
        }).collect(Collectors.toSet());

        String accountId = null;
        if(action.equalsIgnoreCase("edit")) {
            accountId = req.getParameter("accountId");
        }


        String password = "123";
        AccountDto accountDto = new AccountDto(accountId, fullName, password, email, phone, (byte) 1, grantAccess);
        accountService.save(accountDto);

        resp.sendRedirect(req.getContextPath() + "/dashboard");

    }
}
