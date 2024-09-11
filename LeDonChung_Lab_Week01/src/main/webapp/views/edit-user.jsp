<%--@elvariable id="account" type="com.sun.org.apache.xml.internal.security.signature.SignatureProperty"--%>
<%--
  Created by IntelliJ IDEA.
  User: CHUNG
  Date: 9/11/2024
  Time: 9:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <div class="container">
    <h1>Edit User</h1>
    <form action="users" method="post">
        <c:if test="${account.accountId != null}">
            <input type="hidden" name="accountId" value="${account.accountId}">
            <input type="hidden" name="action" value="edit">
        </c:if>
        <c:if test="${account.accountId == null}">
            <input type="hidden" name="action" value="add">
        </c:if>
        <div class="mb-3">
            <label for="fullName" class="form-label">Full name</label>
            <input type="text" class="form-control" id="fullName" name="fullName" value="${account.fullName}">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${account.email}">
        </div>
        <div class="mb-3">
            <label for="phone" class="form-label">Phone</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${account.phone}">
        </div>
        <div class="mb-3">
            <label for="roles" class="form-label">Roles</label>
            <ul id="roles" style="list-style: none" >
                <c:forEach var="role" items="${roles}">
                    <li>
                        <input type="checkbox" id="${role.roleId}" name="roles" value="${role.roleId}"
                               <c:if test="${account.isRole(role.roleId)}">checked</c:if>>
                        <label for="${role.roleId}">${role.roleName}</label>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</body>
</html>
