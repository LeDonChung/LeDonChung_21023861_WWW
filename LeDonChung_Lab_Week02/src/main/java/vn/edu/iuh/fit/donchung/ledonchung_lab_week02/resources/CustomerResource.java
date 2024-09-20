package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {
    @GET
    public Response getAllCustomers() {
        return Response.ok().build();
    }
}
