<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: CHUNG
  Date: 9/23/2024
  Time: 11:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">COLAME</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="products">Sản phẩm</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Giỏ hàng</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="content pt-4 pb-4">
        <div class="row">
            <%--@elvariable id="products" type="java.util.List"--%>
            <c:forEach var="product" items="${products}">
                <div class="col-3 p-2">
                    <div class="card" style="width: 100%; height: 100%">
                        <img src="https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/465191/item/vngoods_56_465191.jpg?width=750"
                             class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text" style="font-weight: bold; font-size: 20px;  "> ${product.name} </p>
                            <p class="card-text" style="font-weight: bold; font-size: 18px; color: red ">
                                <fmt:formatNumber value="${product.getPrice()}" type="currency"  currencySymbol="$"/>
                            </p>
                            <p class="card-text" style="font-weight: bold; font-size: 18px"> ${product.manufacturer} </p>
                            <a href="products?id=${product.id}" class="btn btn-primary">Xem chi tiết</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>

</div>
</body>
</html>
