package vn.edu.iuh.fit.donchung.backend.resources;

import io.swagger.annotations.Api;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.backend.dtos.ProductPriceDto;
import vn.edu.iuh.fit.donchung.backend.services.ProductBeanRemote;

import java.time.LocalDateTime;

@Path("/products")
@Api(value = "/products", tags = "Products")
public class ProductResource {

    @EJB
    private ProductBeanRemote productBean;
    @GET
    public Response getAll() {
        try{
            return Response.ok(productBean.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try{
            ProductDto productDto = productBean.getById(id);
            if(productDto == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Product with id " + id + " not found.").build();
            }
            return Response.ok(productDto).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDto productDto) {
        try {


            return Response.ok(productBean.save(productDto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ProductDto productDto) {
        try {
            return Response.ok(productBean.save(productDto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/{id}/add-price")
    public Response addPrice(@PathParam("id") Long productId, ProductPriceDto productPriceDto) {
        try {
            return Response.ok(productBean.addPrice(productId, productPriceDto)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
