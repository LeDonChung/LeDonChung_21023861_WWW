package vn.edu.iuh.fit.donchung.backend.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.backend.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.backend.dtos.EmployeeDto;
import vn.edu.iuh.fit.donchung.backend.services.EmployeeService;
import vn.edu.iuh.fit.donchung.backend.utils.AppUtils;

import java.util.List;

@Path("/employees")
public class EmployeeResource {
    @Inject
    private EmployeeService employeeService;
    @GET
    public Response getAll() {
        try {
            List<EmployeeDto> employees = employeeService.getAll();
            return Response.ok()
                    .entity(employees)
                    .build();
        } catch (Exception e) {
            return jakarta.ws.rs.core.Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            EmployeeDto employee = employeeService.getById(id);
            if(employee == null) {
                return Response.status(jakarta.ws.rs.core.Response.Status.NOT_FOUND)
                        .entity("Employee with id " + id + " not found")
                        .build();
            }
            return Response.ok()
                    .entity(employee)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(EmployeeDto employee) {
        try {
            employee = employeeService.save(employee);
            return Response.status(Response.Status.CREATED)
                    .entity(employee)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(EmployeeDto employee) {
        try {
            employee = employeeService.save(employee);
            return Response.status(Response.Status.OK)
                    .entity(employee)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }

    @PUT
    @Path("/updateStatus/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateStatus(@PathParam("id") Long id, @QueryParam("status") String status){
        try {
            EmployeeDto employee = employeeService.updateStatus(id, EmployeeStatus.valueOf(status));
            if(employee == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Employee with id " + id + " not found")
                        .build();
            }
            return Response.ok()
                    .entity(employee)
                    .build();
        } catch (Exception e) {
            return Response.serverError()
                    .entity(AppUtils.SERVER_ERROR)
                    .build();
        }
    }
}
