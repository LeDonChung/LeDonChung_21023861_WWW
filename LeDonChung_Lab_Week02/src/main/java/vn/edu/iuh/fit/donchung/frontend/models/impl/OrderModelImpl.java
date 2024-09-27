package vn.edu.iuh.fit.donchung.frontend.models.impl;

import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;
import vn.edu.iuh.fit.donchung.frontend.constants.SystemConstant;
import vn.edu.iuh.fit.donchung.frontend.models.OrderModel;

import java.util.List;

@Stateless
public class OrderModelImpl implements OrderModel {
    @Override
    public OrderDto save(OrderDto orderDto) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/orders";
            Response response = client.target(SystemConstant.API_URL)
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(orderDto, MediaType.APPLICATION_JSON));
            if(response.getStatus() == Response.Status.CREATED.getStatusCode() || response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(OrderDto.class);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<OrderDto> getAll() {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/orders";
            Response response = client.target(SystemConstant.API_URL)
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return null;
            }
        }
    }

    @Override
    public OrderDto getById(Long id) {
        try(Client client = ClientBuilder.newClient()) {
            String PATH = "/api/orders/" + id.toString();
            Response response = client.target(SystemConstant.API_URL)
                    .path(PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            if(response.getStatus() == Response.Status.OK.getStatusCode()){
                return response.readEntity(new GenericType<>() {
                });
            } else {
                return null;
            }
        }
    }
}
