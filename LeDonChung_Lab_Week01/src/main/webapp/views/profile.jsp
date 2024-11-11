<%@ page import="vn.edu.iuh.fit.donchung.ledonchung_lab_week01.dtos.AccountDto" %>
<%@ page import="java.util.stream.Collectors" %><%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/7/2024
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-light bg-light">
        <c:if test="${not empty sessionScope.get('account')}">
            <p style='text-align: center'>
                <c:out value="Welcome, ${sessionScope.get('account').fullName}"/>
            </p>
        </c:if>
        <c:if test="${empty sessionScope.get('account')}">
            <p style='text-align: center'>
                <c:out value="Welcome, Guest"/>
            </p>
        </c:if>

        <form action="control" method="post">
            <input type="hidden" name="action" value="logout"/>
            <button class="btn btn-warning">Logout</button>
        </form>
    </nav>
    <c:if test="${not empty sessionScope.get('account')}">
        <div style='margin: 100px 0'>
            <p style=''>Full name: ${sessionScope.get('account').fullName}</p>
            <p style=''>Email: ${sessionScope.get('account').email}</p>
            <p style=''>Phone: ${sessionScope.get('account').phone}</p>
            <p> Role:
                <c:forEach items="${sessionScope.get('account').grantAccesses}" var="item">
                    <span class="badge bg-secondary">${item.role.roleName}</span>
                </c:forEach>
            </p>
        </div>
    </c:if>
</div>
</body>
</html>
