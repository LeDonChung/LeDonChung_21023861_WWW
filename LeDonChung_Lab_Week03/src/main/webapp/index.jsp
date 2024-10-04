<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Login Page</title>

    <!-- Custom fonts for this template-->

    <link href="<c:url  value="resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url  value="resources/css/sb-admin-2.min.css"/>" rel="stylesheet" type="text/css">

</head>

<body id="bg-gradient-primary">
<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image">
                            <img src="https://picsum.photos/200/300?random=2" alt="" style="width: 100%;height: 100%">
                        </div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">Chào mừng bạn!</h1>
                                </div>
                                <c:if test="${not empty message}">
                                    <div class="alert alert-danger" role="alert">
                                        ${message}
                                    </div>
                                </c:if>
                                <form class="user" action="account" method="post">
                                    <input type="hidden" name="action" value="login">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               id="phone" aria-describedby="emailHelp" value="0867713557" name="phone"
                                               placeholder="Số điện thoại">
                                    </div>

                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Đăng nhập
                                    </button>

                                </form>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="register.html">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>



<!-- Bootstrap core JavaScript-->
<script src="<c:url  value="resources/vendor/jquery/jquery.min.js"/>"></script>

<script src="<c:url  value="resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->

<script src="<c:url  value="resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url  value="resources/js/sb-admin-2.min.js"/>"></script>

<!-- Page level plugins -->

<script src="<c:url  value="resources/vendor/chart.js/Chart.min.js"/>"></script>

<!-- Page level custom scripts -->

<script src="<c:url  value="resources/js/demo/chart-area-demo.js"/>"></script>

<script src="<c:url  value="resources/js/demo/chart-pie-demo.js"/>"></script>

</body>

</html>