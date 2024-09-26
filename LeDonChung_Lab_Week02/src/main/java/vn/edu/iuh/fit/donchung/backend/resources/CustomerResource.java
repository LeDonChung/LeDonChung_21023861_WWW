package vn.edu.iuh.fit.donchung.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.services.CustomerService;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;
import vn.edu.iuh.fit.donchung.backend.dtos.CustomerDto;

import java.util.List;

@Path("/customers")
public class CustomerResource {
    @Inject
    private CustomerService customerService;
    @GET
    public Response getAllCustomers() {
        try {
            List<CustomerDto> customers = customerService.getAll();
            return Response.ok()
                    .entity(customers)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }

    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") Long id) {
        try {
            CustomerDto customer = customerService.getById(id);
            if(customer == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Customer with id " + id + " not found")
                        .build();
            }
            return Response.ok()
                    .entity(customer)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CustomerDto customer) {
        try {
            customer = customerService.save(customer);
            return Response.status(Response.Status.CREATED)
                    .entity(customer)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(CustomerDto customer) {
        try {
            customer = customerService.save(customer);
            return Response.status(Response.Status.OK)
                    .entity(customer)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            boolean result = customerService.delete(id);
            return Response.ok()
                    .entity(result)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

}
