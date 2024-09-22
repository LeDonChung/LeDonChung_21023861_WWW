package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.dtos.ProductDto;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.ProductStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Product;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPrice;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.ProductPriceId;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.ProductService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.utils.AppUtils;

@Path("/products")
public class ProductResource {
    @Inject
    private ProductService productService;

    @GET
    public Response getAllProducts() {
        try {
            return Response.ok()
                    .entity(productService.getAll())
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        try {
            return Response.ok()
                    .entity(productService.getById(id))
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(ProductDto product) {
        try {
            product = productService.save(product);
            return Response.status(Response.Status.CREATED)
                    .entity(product)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ProductDto product) {
        try {
            product = productService.save(product);
            return Response.status(Response.Status.OK)
                    .entity(product)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") String status) {
        try {
            boolean result = productService.updateStatus(id, ProductStatus.valueOf(status));
            return Response.status(Response.Status.OK)
                    .entity(result)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Path("/{id}/price")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertPrice(@PathParam("id") Long id, ProductPrice productPrice) {
        try {
            ProductPriceId productPriceId = new ProductPriceId();
            productPriceId.setProductId(id);
            productPrice.setId(productPriceId);

            ProductDto product = productService.savePrice(productPrice);
            return Response.status(Response.Status.OK)
                    .entity(product)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

}
