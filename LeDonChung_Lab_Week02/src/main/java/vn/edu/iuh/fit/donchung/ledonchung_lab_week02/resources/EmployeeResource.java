package vn.edu.iuh.fit.donchung.ledonchung_lab_week02.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.enums.EmployeeStatus;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.models.Employee;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.services.EmployeeService;
import vn.edu.iuh.fit.donchung.ledonchung_lab_week02.utils.AppUtils;

import java.util.List;

@Path("/employees")
public class EmployeeResource {
    @Inject
    private EmployeeService employeeService;
    @GET
    public Response getAll() {
        try {
            List<Employee> employees = employeeService.getAll();
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
            Employee employee = employeeService.getById(id);
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
    public Response create(Employee employee) {
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
    public Response update(Employee employee) {
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
            Employee employee = employeeService.updateStatus(id, EmployeeStatus.valueOf(status));
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
