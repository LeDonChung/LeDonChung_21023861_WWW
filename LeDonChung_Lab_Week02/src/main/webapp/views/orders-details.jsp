<%@ page import="vn.edu.iuh.fit.donchung.frontend.models.Cart" %>
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

    <title>SB Admin 2 - Dashboard</title>

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
            <div class="sidebar-brand-text mx-3">Lab Week <sup>2</sup></div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Dashboard -->
        <li class="nav-item active">
            <a class="nav-link" href="orders">
                <i class="fas fa-fw fa-tachometer-alt"></i>
                <span>Bán hàng</span></a>
        </li>

        <!-- Nav Item - Show Full Order -->
        <li class="nav-item">
            <a class="nav-link" href="orders?action=show">
                <i class="fa-solid fa-caravan"></i>
                <span>Đơn hàng</span></a>
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
                            <img class="img-profile rounded-circle" src="<c:url value="https://scontent.fsgn5-14.fna.fbcdn.net/v/t39.30808-6/361366862_1607093663105601_7835049158388472986_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=UyuzQsr89cQQ7kNvgHXB1o1&_nc_ht=scontent.fsgn5-14.fna&_nc_gid=Apzzttq89Da7VLZAlF6IdG_&oh=00_AYBlOaEmaSbADSQ2y2rrT3ilzpbdBVz0LTsET9RejtL2xQ&oe=66FCA263" />" alt="">
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
            <div class="container-fluid" style="background-color: #fff;">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4 pt-3">
                    <h1 class="h3 mb-0 text-gray-800">Bán hàng</h1>
                </div>


                <!-- Content Row -->
                <div class="row">
                    <div class="col-7">
                        <%--@elvariable id="order" type="vn.edu.iuh.fit.donchung.backend.dtos.OrderDto"--%>
                        <c:forEach items="${order.orderDetails}" var="detail">
                            <div class="card"
                                 style="display: flex; flex-direction: row; align-items: center; margin: 10px 0;">
                                <img src="${detail.product.productImages.get(0).path}"
                                     style="height: 150px; width: 150px; margin-left: 10px;" class="card-img-top"
                                     alt="Màn hình Gaming ASRock Challenger">
                                <div class="card-body">
                                    <h5 class="card-title">${detail.product.name}
                                    </h5>
                                    <p class="card-text">
                                            <span class="text-danger">
                                                <fmt:setLocale value="vi-VN"/>
                                                <fmt:formatNumber value="${detail.price}" type="currency"/>
                                            </span>
                                    </p>
                                    <div class="input-group mb-3" style="width: 250px;">
                                        <form action="cart" method="get">
                                            <label>
                                                <input type="number" max="10" min="1" class="form-control text-center"
                                                       name="quantity" value="<fmt:formatNumber value="${detail.quantity}" type="number" maxFractionDigits="0"/>" disabled
                                                       onchange="this.form.submit();">
                                            </label>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="col-5">

                        <div class="card" style="display: flex; margin: 20px 0">
                            <div class="card-body">
                                <h5 class="card-title"><b>Tổng đơn hàng | ${order.getTotal()} Sản
                                    phẩm</b>
                                </h5>
                                <p class="card-text">
                                    Tổng cộng <span class="text-danger">
                                        <fmt:setLocale value="vi-VN"/>
                                        <fmt:formatNumber value="${order.getTotalPrice()}"
                                                          type="currency"/>
                                </span>
                                </p>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                           id="flexRadioDefault1" checked>
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Tiền mặt
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                           id="flexRadioDefault2">
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Ví điện tử
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                           id="flexRadioDefault2" checked>
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Thẻ ngân hàng
                                    </label>
                                </div>
                            </div>
                        </div>

                        <form class="d-none d-sm-inline-block form-inline navbar-search"
                              style="width: 100%; border: 1px solid; border-radius: 5px; margin-bottom: 20px;"
                              method="get" action="orders">
                            <div class="input-group">
                                <input name="action" type="hidden" value="getCustomerByPhone">
                                <input type="text" class="form-control bg-light border-0 small"
                                       placeholder="Tìm kiếm bằng số điện thoại" aria-label="Search" name="phone"
                                       aria-describedby="basic-addon2">
                                <div class="input-group-append"
                                     style="border-radius: 5px; border-radius: 1px solid;">
                                    <button class="btn btn-primary" type="submit">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                </div>
                            </div>
                            <c:if test="${not empty message} ">
                                <p class="alert alert-danger">${message}</p>
                            </c:if>
                        </form>

                        <form action="orders">
                            <div class="mb-3">

                                <label for="name" class="form-label">Họ và tên</label>
                                <input type="text" class="form-control" id="name" name="name"
                                       value="${order.customer.name}" aria-describedby="name">
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">Số điện thoại</label>
                                <input type="text" class="form-control" id="phone" name="phone"
                                       value="${order.customer.phone}">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="text" class="form-control" id="email" name="email"
                                       value="${order.customer.email}">
                            </div>
                            <div class="mb-3">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <input type="text" class="form-control" id="address" name="address"
                                       value="${order.customer.address}">
                            </div>
                            <c:if test="${order.customer.id != null}">
                                <div class="mb-3 form-check">
                                    <input type="checkbox" class="form-check-input" id="newCustomer" disabled>
                                    <label class="form-check-label" for="newCustomer">Khách hàng mới</label>
                                </div>
                            </c:if>
                            <c:if test="${order.customer.id == null}">
                                <div class="mb-3 form-check">
                                    <input type="checkbox" class="form-check-input" id="newCustomer" checked disabled>
                                    <label class="form-check-label" for="newCustomer">Khách hàng mới</label>
                                </div>
                            </c:if>
                            <button type="button" class="btn btn-primary" style="width: 100%">Đặt hàng</button>
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
<!-- Logout Modal-->
<div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document" style="max-width: 900px; margin: 0 400px;">
        <div class="modal-content" style="min-height: 900px;     width: 900px;">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    <!-- Topbar Search -->
                    <form
                            class="d-none d-sm-inline-block form-inline mr -auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small"
                                   placeholder="Tìm kiếm sản phẩm" aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row" style="height: 900px;">
                    <c:forEach var="product" items="${products}">

                        <div class="col-3">
                            <form action="cart" method="get">
                                <div class="card" style="height: 100px">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="hidden" name="quantity" value="1">

                                    <c:if test="${product.productImages.size() > 0}">
                                        <img src="${product.productImages.get(0).path}" style="height: 200px"
                                             class="card-img-top" alt="..."/>
                                    </c:if>
                                    <c:if test="${product.productImages.size() <= 0}">
                                        <img src=""
                                             class="card-img-top" alt="..." style="height: 50px"/>
                                    </c:if>
                                    <div class="card-body">
                                        <p class="card-text"> ${product.name} </p>
                                        <p class="card-text" style="color: red ">
                                            <fmt:setLocale value="vi-VN"/>
                                            <fmt:formatNumber value="${product.price}" type="currency"/>
                                        </p>
                                        <p class="card-text"> ${product.manufacturer} </p>
                                        <button class="btn btn-primary" type="submit">Thêm vào giỏ</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </c:forEach>

                </div>

            </div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>
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

<script src="<c:url  value="/resources/js/demo/chart-area-demo.js"/>"></script>

<script src="<c:url  value="/resources/js/demo/chart-pie-demo.js"/>"></script>

</body>

</html>