package vn.edu.iuh.fit.donchung.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.frontend.constants.SystemConstant;
import vn.edu.iuh.fit.donchung.frontend.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProductModelImpl implements ProductModel {
    @Override
    public List<ProductDto> findAll() {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products/active";
            Response response = client.target(SystemConstant.API_URL)
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return new ArrayList<>();
            }
        }
    }

    @Override
    public ProductDto findById(Long id) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/products/" + id.toString();
            Response response = client.target(SystemConstant.API_URL)
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()) {
                return response.readEntity(ProductDto.class);
            } else {
                return new ProductDto();
            }
        }
    }
}
