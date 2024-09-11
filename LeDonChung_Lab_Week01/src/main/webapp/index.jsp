<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container">
    <%

        if(request.getAttribute("error") != null){
            String error = (String) request.getAttribute("error");
            out.print("<div class='alert alert-danger' role='alert'>" + error + "</div>");
        }
        if(request.getAttribute("message") != null){
            String message = (String) request.getAttribute("message");
            out.print("<div class='alert' role='alert'>" + message + "</div>");
        }
    %>

    <form action="control" method="post" style="width: 50%; margin: auto;">
        <input type="hidden" value="login" name="action">
        <div class="mb-3">
            <label for="accountId" class="form-label">Username</label>
            <input type="text" class="form-control" name="accountId" id="accountId" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>

</body>
</html>