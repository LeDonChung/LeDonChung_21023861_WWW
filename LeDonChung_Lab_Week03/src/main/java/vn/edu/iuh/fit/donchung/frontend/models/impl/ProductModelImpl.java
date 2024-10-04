package vn.edu.iuh.fit.donchung.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.frontend.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductModelImpl implements ProductModel {
    @Override
    public List<ProductDto> findAll() {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products";
            Response response = client.target("http://localhost:8080/LeDonChung_Lab_Week03-1.0-SNAPSHOT")
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return new ArrayList<>();
            }
        }
    }

    @Override
    public ProductDto addPrice(Long productId, ProductPriceDto productPriceDto) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products/" + productId.toString() + "/add-price";
            Response response = client.target("http://localhost:8080/LeDonChung_Lab_Week03-1.0-SNAPSHOT")
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(productPriceDto, MediaType.APPLICATION_JSON));
            if(response.getStatus() == Response.Status.CREATED.getStatusCode() || response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public ProductDto findById(Long id) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products/" + id.toString();
            Response response = client.target("http://localhost:8080/LeDonChung_Lab_Week03-1.0-SNAPSHOT")
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(ProductDto.class);
            } else {
                return new ProductDto();
            }
        }
    }

    @Override
    public ProductDto save(ProductDto productDto) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products";
            Response response = client.target("http://localhost:8080/LeDonChung_Lab_Week03-1.0-SNAPSHOT")
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(productDto, MediaType.APPLICATION_JSON));
            if(response.getStatus() == Response.Status.CREATED.getStatusCode() || response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(ProductDto.class);
            } else {
                return null;
            }
        }
    }
}
