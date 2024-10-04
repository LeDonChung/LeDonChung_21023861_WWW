<%@ page language="java" contentType="text/html;charset=UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Edit Product</title>

    <!-- Custom fonts for this template-->

    <link href="<c:url  value="/resources/vendor/fontawesome-free/css/all.min.css"/>" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="<c:url  value="/resources/css/sb-admin-2.min.css"/>" rel="stylesheet" type="text/css">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">Lab Week <sup>3</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">


        <!-- Nav Item - Show Full Order -->
        <li class="nav-item active">
            <a class="nav-link" href="products">
                <i class="fa-solid fa-caravan"></i>
                <span>Sản phẩm</span></a>
        </li>

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                    <i class="fa fa-bars"></i>
                </button>

                <!-- Topbar Search -->
                <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                    <div class="input-group">
                        <p class="alert alert-success mb-0">
                            Người thực hiện: Lê Đôn Chủng - 21023861
                        </p>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">


                    <div class="topbar-divider d-none d-sm-block"></div>

                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.get('account').getFullName()}</span>
                            <img class="img-profile rounded-circle"
                                 src="<c:url value="https://scontent.fsgn5-14.fna.fbcdn.net/v/t39.30808-6/361366862_1607093663105601_7835049158388472986_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=UyuzQsr89cQQ7kNvgHXB1o1&_nc_ht=scontent.fsgn5-14.fna&_nc_gid=Apzzttq89Da7VLZAlF6IdG_&oh=00_AYBlOaEmaSbADSQ2y2rrT3ilzpbdBVz0LTsET9RejtL2xQ&oe=66FCA263" />"
                                 alt="">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                             aria-labelledby="userDropdown">
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                Profile
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                Settings
                            </a>
                            <a class="dropdown-item" href="#">
                                <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                Activity Log
                            </a>
                            <div class="dropdown-divider"></div>
                            <form action="account">
                                <input type="hidden" name="action" value="logout">

                                <button class="dropdown-item" type="submit" data-toggle="modal"
                                        data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </button>
                            </form>
                        </div>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Sản phẩm</h1>

                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3"
                         style="display: flex; justify-content: flex-start; align-items: center">
                        <c:if test="${not empty product.id}">
                            <h6 class="m-0 font-weight-bold text-primary">Chỉnh sửa sản phẩm</h6>
                        </c:if>

                        <c:if test="${empty product.id}">
                            <h6 class="m-0 font-weight-bold text-primary">Thêm sản phẩm</h6>
                        </c:if>
                    </div>
                    <div class="card-body">

                        <form action="products" method="post">

                            <c:if test="${not empty product.id}">
                                <input type="hidden" name="id" value="${product.id}">
                            </c:if>
                            <div class="form-group">
                                <label for="name">Tên sản phẩm</label>
                                <input type="text" class="form-control" id="name" name="name" value="${product.name}">
                            </div>
                            <div class="form-group">
                                <label for="imgPath">Hình ảnh</label>
                                <input type="text" class="form-control" id="imgPath" name="imgPath" value="${product.imgPath}">
                            </div>


                            <div class="form-group">
                                <label for="manufacturer">Thương hiệu</label>
                                <input type="text" class="form-control" id="manufacturer" name="manufacturer" value="${product.manufacturer}">
                            </div>

                            <div class="form-group">
                                <label for="unit">Đơn vị</label>
                                <input type="text" class="form-control" id="unit" name="unit" value="${product.unit}">
                            </div>

                            <div class="form-group">
                                <label for="price">Giá</label>
                                <input type="text" class="form-control" id="price" name="price" value="${product.price}">
                            </div>
                            <button class="btn btn-primary" type="submit">Submit</button>
                        </form>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright &copy; Your Website 2024</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->
<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script src="<c:url  value="/resources/vendor/jquery/jquery.min.js"/>"></script>

<script src="<c:url  value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"/>"></script>

<!-- Core plugin JavaScript-->

<script src="<c:url  value="/resources/vendor/jquery-easing/jquery.easing.min.js"/>"></script>

<!-- Custom scripts for all pages-->
<script src="<c:url  value="/resources/js/sb-admin-2.min.js"/>"></script>

<!-- Page level plugins -->

<script src="<c:url  value="/resources/vendor/chart.js/Chart.min.js"/>"></script>

<!-- Page level custom scripts -->

<!-- Page level plugins -->

<script src="<c:url  value="/resources/vendor/datatables/jquery.dataTables.min.js"/>"></script>
<script src="<c:url  value="/resources/vendor/datatables/dataTables.bootstrap4.min.js"/>"></script>

<!-- Page level custom scripts -->
<script src="<c:url  value="/resources/js/demo/datatables-demo.js"/>"></script>

</body>

</html>