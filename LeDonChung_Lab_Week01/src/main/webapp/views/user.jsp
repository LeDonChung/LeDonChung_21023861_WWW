<%@ page import="vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-light">
            <p style="text-align: center">
                <% AccountDto dto = (AccountDto) session.getAttribute("account");
                    String welcome = "";
                    if (dto != null) {
                        welcome = "<p>" + "Welcome, " + dto.getFullName() + "</p>";
                    } else {
                        welcome = "<p>" + "Welcome, Guest</p>";
                    }
                    out.print(welcome);
                %>
            </p>
            <form action="control" method="post">
                <input type="hidden" name="action" value="logout"/>
                <button class="btn btn-warning">Logout</button>
            </form>
    </nav>

    <%
        if (dto != null) {
            String html = "<div style='margin: 100px 0'>";
            html += "<p style=''>Full name: " + dto.getFullName() + "</p>";
            html += "<p style=''>Email: " + dto.getEmail() + "</p>";
            html += "<p style=''>Phone: " + dto.getPhone() + "</p>";
            html += "<p> Role: " +
                    dto.getGrantAccesses().stream()
                    .map(grantAccessDto -> "<span>" + grantAccessDto.getRole().getRoleName() + "</span>")
                    .reduce((s, s2) -> s + " " + s2).orElse("")
                    .trim()
                    + "</p>";
            html += "</div>";
            out.print(html);
        }
    %>
</div>
</body>
</html>
