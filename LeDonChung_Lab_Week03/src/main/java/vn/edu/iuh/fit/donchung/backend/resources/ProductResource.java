package vn.edu.iuh.fit.donchung.backend.resources;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.services.ProductBeanRemote;

@Path("/products")
public class ProductResource {

    @EJB
    private ProductBeanRemote productBean;
    @GET
    public Response getAll() {
        return Response.ok(productBean.getAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        return Response.ok(productBean.getById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDto productDto) {
        return Response.ok(productBean.save(productDto)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ProductDto productDto) {
        return Response.ok(productBean.save(productDto)).build();
    }
}
