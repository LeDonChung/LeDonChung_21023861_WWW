package vn.edu.iuh.fit.donchung.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.frontend.models.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "OrderController", urlPatterns = "/orders")
public class OrderController extends HttpServlet {
    @EJB
    private ProductModel productModel;
    @EJB
    private CustomerModel customerModel;
    @EJB
    private CartModel cartModel;
    @EJB
    private OrderModel orderModel;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(session.getAttribute("account") == null) {
            req.setAttribute("message", "Vui lòng đăng nhập!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        String action = req.getParameter("action");

        if(action!= null) {
            if(action.equalsIgnoreCase("getCustomerByPhone")) {
                String phone = req.getParameter("phone");
                CustomerDto customerDto = customerModel.findByPhone(phone);
                if(customerDto == null) {
                    req.setAttribute("message", "Không tìm thấy khách hàng!");
                } else {
                    cart.setCustomerDto(customerDto);
                }
            } else if(action.equalsIgnoreCase("checkout")) {
                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String address = req.getParameter("address");
                String email = req.getParameter("email");
                String id = req.getParameter("customerId");

                CustomerDto customerDto
                        = CustomerDto.builder()
                        .name(name)
                        .phone(phone)
                        .address(address)
                        .email(email)
                        .build();

                if(id != null) {
                    customerDto.setId(Long.parseLong(id));
                }

                cart.setCustomerDto(customerDto);
                session.setAttribute("cart", cart);
                OrderDto order = cartModel.toDto(req);
                orderModel.save(order);
                cartModel.clearCart(req);
            } else if(action.equalsIgnoreCase("show")) {
                List<OrderDto> orders = orderModel.getAll();
                req.setAttribute("orders", orders);
                req.getRequestDispatcher("/views/orders-all.jsp").forward(req, resp);
            } else if(action.equalsIgnoreCase("detail")) {
                String id = req.getParameter("id");
                OrderDto order = orderModel.getById(Long.parseLong(id));
                req.setAttribute("order", order);
                req.getRequestDispatcher("/views/orders-details.jsp").forward(req, resp);
            }


        }


        if(cart == null) {
            cart = new Cart();
            cart.setOrderDate(LocalDateTime.now());
            cart.setCartDetails(
                    new ArrayList<>()
            );
            cart.setCustomerDto(
                    new CustomerDto()
            );
            session.setAttribute("cart", cart);
        }


        List<ProductDto> products = productModel.findAll();

        req.setAttribute("products", products);
        session.setAttribute("cart", cart);
        req.getRequestDispatcher("/views/orders.jsp").forward(req, resp);
    }
}
