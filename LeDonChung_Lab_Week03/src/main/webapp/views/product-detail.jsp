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
            <div class="col-6">
                <div id="carouselExampleIndicators" style="" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-indicators">
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                    </div>
                    <div class="carousel-inner">
                        <%--@elvariable id="product" type="vn.edu.iuh.fit.donchung.backend.dtos.ProductDto"--%>
                        <c:forEach var="image" items="${product.productImages}" varStatus="loop">
                            <c:if test="${loop.index == 0}">
                                <div class="carousel-item active">
                                    <img src="https://im.uniqlo.com/global-cms/spa/resc6714b30ca663d7d862d0c595867e8a2fr.jpg" class="d-block w-100" alt="...">
                                </div>
                            </c:if>

                            <c:if test="${loop.index != 0}">
                                <div class="carousel-item">
                                    <img src="https://im.uniqlo.com/global-cms/spa/res775ff067e485f683936411a64318b768fr.jpg" class="d-block w-100" alt="...">
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
            <div class="col-6" style="display: flex; flex-direction: column; justify-content: space-between">
                <p style="font-weight: bold; font-size: 20px;  ">${product.name}</p>
                <p style="font-size: 18px; ">Nhãn hàng: ${product.manufacturer}</p>
                <p style="font-weight: bold; font-size: 20px; color: red  ">Giá: ${product.getPriceLatest()}</p>
                <p style="font-size: 18px; font-weight: bold;">SỐ LƯỢNG</p>
                <select class="form-select" aria-label="Số lượng" style="width: 200px">
                    <option value="1" selected>1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="3">4</option>
                    <option value="3">5</option>
                    <option value="3">6</option>
                    <option value="3">7</option>
                    <option value="3">8</option>
                    <option value="3">9</option>
                    <option value="3">10</option>
                </select>
                <button class="btn btn-primary" style="width: 200px; margin: 20px 0">Thêm vào giỏ hàng</button>
                <p>Mô tả: </p>
                <p>${product.description}</p>
            </div>
        </div>

    </div>
</body>
</html>
