package vn.edu.iuh.fit.donchung.frontend.models.impl;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.donchung.backend.dtos.*;
import vn.edu.iuh.fit.donchung.frontend.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CartModelImpl implements CartModel {
    @EJB
    private ProductModel productModel;
    @Override
    public void addProduct(Long productId, String note, Long quantity, HttpServletRequest req) {
        Cart cart = getCart(req);

        // nếu tồn tại thì cập nhật
        // nếu không tồn tại thì thêm mới

        // kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        List<CartDetail> cartDetails = cart.getCartDetails();
        boolean isExist = false;
        for (CartDetail cartDetail : cartDetails) {
            if(cartDetail.getProductDto().getId().equals(productId)) {
                cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                isExist = true;
                break;
            }
        }

        ProductDto productDto = productModel.findById(productId);
        if(!isExist) {
            cartDetails.add(
                    new CartDetail(
                            productDto,
                            note,
                            productDto.getPrice(),
                            quantity
                    )
            );
        }

        cart.setCartDetails(cartDetails);

        req.getSession().setAttribute("cart", cart);
    }

    @Override
    public void removeProduct(Long productId, HttpServletRequest req) {
        Cart cart = getCart(req);
        cart.getCartDetails().removeIf(
                cartDetail -> cartDetail.getProductDto().getId().equals(productId)
        );
        req.getSession().setAttribute("cart", cart);
    }

    @Override
    public void updateProduct(Long productId, Long quantity, HttpServletRequest req) {
        Cart cart = getCart(req);
        cart.getCartDetails().forEach(
                cartDetail -> {
                    if(cartDetail.getProductDto().getId().equals(productId)) {
                        cartDetail.setQuantity(quantity);
                    }
                }
        );
        req.getSession().setAttribute("cart", cart);
    }

    @Override
    public void clearCart(HttpServletRequest req) {
        req.getSession().removeAttribute("cart");
        Cart cart = getCart(req);
        cart.setCartDetails(
                new ArrayList<>()
        );
        req.getSession().setAttribute("cart", cart);
    }

    @Override
    public Cart getCart(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            cart.setCartDetails(
                    new ArrayList<>()
            );
            cart.setOrderDate(LocalDateTime.now());
            cart.setCustomerDto(
                    new CustomerDto()
            );
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public OrderDto toDto(HttpServletRequest req) {
        Cart cart = getCart(req);
        OrderDto orderDto = new OrderDto();
        Account account = (Account) req.getSession().getAttribute("account");
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(account.getId());

        orderDto.setEmployee(employeeDto);
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setCustomer(cart.getCustomerDto());
        orderDto.setOrderDetails(
                cart.getCartDetails().stream().map(
                        cartDetail -> {
                            OrderDetailDto orderDetailDto = new OrderDetailDto();
                            orderDetailDto.setProductId(cartDetail.getProductDto().getId());
                            orderDetailDto.setQuantity(Double.valueOf(cartDetail.getQuantity()));
                            orderDetailDto.setPrice(cartDetail.getPrice());
                            orderDetailDto.setNote(cartDetail.getNote());
                            return orderDetailDto;
                        }
                ).collect(Collectors.toList())
        );

        return orderDto;
    }
}
