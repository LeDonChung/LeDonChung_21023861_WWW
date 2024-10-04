package vn.edu.iuh.fit.donchung.frontend.controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.frontend.models.ProductModel;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    @EJB
    private ProductModel productModel;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String url = "views/products.jsp";
        ProductDto productDto = new ProductDto();
        if (action != null) {
            url = "views/edit-product.jsp";
            if (action.equalsIgnoreCase("edit")) {
                Long id = Long.parseLong(req.getParameter("id"));
                productDto = productModel.findById(id);
                req.setAttribute("product", productDto);
            }
        } else {
            List<ProductDto> products = productModel.findAll();
            req.setAttribute("products", products);
        }

        req.getRequestDispatcher(url).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action != null) {
            if(action.equalsIgnoreCase("addPrice")) {
                String productId = req.getParameter("id");
                String price = req.getParameter("price");
                ProductPriceDto productPriceDto = new ProductPriceDto();
                productPriceDto.setValue(Double.parseDouble(price));
                productModel.addPrice(Long.parseLong(productId), productPriceDto);
            }
        } else {
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String price = req.getParameter("price");
            String imgPath = req.getParameter("imgPath");
            String manufacturer = req.getParameter("manufacturer");
            String unit = req.getParameter("unit");


            ProductDto productDto = new ProductDto(
                    id == null || id.isEmpty() ? null : Long.parseLong(id),
                    description, manufacturer, name, unit, imgPath, Double.parseDouble(price)
            );

            productModel.save(productDto);
        }


        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
