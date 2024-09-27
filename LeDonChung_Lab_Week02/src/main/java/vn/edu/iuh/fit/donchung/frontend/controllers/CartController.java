package vn.edu.iuh.fit.donchung.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.frontend.models.CartModel;
import vn.edu.iuh.fit.donchung.frontend.models.ProductModel;

import java.io.IOException;

@WebServlet(name = "CartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {
    @EJB
    private ProductModel productModel;
    @EJB
    private CartModel cartModel;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "add": {
                String productId = req.getParameter("productId");
                String quantity = req.getParameter("quantity");
                String note = req.getParameter("");
                cartModel.addProduct(Long.parseLong(productId), note, Long.parseLong(quantity), req);
                break;
            }
            case "remove": {
                String productId = req.getParameter("productId");
                cartModel.removeProduct(Long.parseLong(productId), req);
                break;
            }
            case "update": {
                String productId = req.getParameter("productId");
                String quantity = req.getParameter("quantity");
                cartModel.updateProduct(Long.parseLong(productId), Long.parseLong(quantity), req);
                break;
            }
            case "clear":
                cartModel.clearCart(req);
                break;
        }

        resp.sendRedirect(req.getContextPath() + "/orders");
    }
}
