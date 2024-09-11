<%@ page import="vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <title>Dashboard</title>
    <link href="https://fonts.googleapis.com/css?family=DM+Sans:400,500,700&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <ul class="nav" style="justify-content: space-between;">
        <li class="nav-item">
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
        </li>
        <li class="nav-item">
            <form action="control" method="post">
                <input type="hidden" name="action" value="logout"/>
                <button class="btn btn-warning">Logout</button>
            </form>
        </li>

    </ul>
    <div class="page-content">
        <div class="header" style="text-align: start; margin: 20px 0;">
            <p style="font-size: 20px; font-weight: bold">Manager Users</p>
        </div>
        <a class="btn btn-primary" href="users?action=add">ADD</a>
        <table class="table">
            <thead>
            <tr>
                <th>Full name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td><c:out value="${account.fullName}"/></td>
                    <td><c:out value="${account.email}"/></td>
                    <td><c:out value="${account.phone}"/></td>
                    <td><c:out value="${account.status}"/></td>
                    <td>
                        <a class="btn btn-primary" href="users?action=edit&accountId=${ account.accountId }">Edit</a>
                        <a class="btn btn-warning" href="">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
