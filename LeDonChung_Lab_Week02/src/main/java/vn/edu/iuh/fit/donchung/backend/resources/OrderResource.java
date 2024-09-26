package vn.edu.iuh.fit.donchung.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.dtos.OrderDto;
import vn.edu.iuh.fit.donchung.backend.services.OrderService;

@Path("/orders")
public class OrderResource {
    @Inject
    private OrderService orderService;

    /**
     * API: GET /orders
     * Lấy tất cả các đơn hàng
     * @return danh sách đơn hàng
     * @return
     */
    @GET
    public Response getAll() {
        try {
            return Response.ok(orderService.findAll()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }

    }

    /**
     * API: POST /orders
     * @param order đơn hàng cần tạo
     * @return đơn hàng đã được tạo
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(OrderDto order) {
        try {
            return Response.ok(orderService.save(order)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    /**
     * API: GET /orders/{id}
     * Lấy đơn hàng theo id
     * @param id id của đơn hàng cần lấy
     * @return đơn hàng
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(orderService.findById(id)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }
}
