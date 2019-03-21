package com.modulo.accidentes.controller;

import com.modulo.accidentes.dto.EmpleadoDto;
import com.modulo.accidentes.model.Empleado;
import com.modulo.accidentes.service.EmpleadoService;
import com.modulo.accidentes.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/empleados")
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoController extends GenericController<Empleado, EmpleadoDto> {

    private EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Override
    @GET
    public List<EmpleadoDto> getAll() {
        return super.getAll();
    }

    @Path("/create")
    @POST
    public Response create(@RequestBody Empleado empleado) {
        empleadoService.save(empleado);
        return Response.ok("se creo un nuevo empleado !!").build();
    }

    @Path("/update")
    @PUT
    public Response actualizar(@RequestBody Empleado nuevoEmpleado) {
        empleadoService.update(nuevoEmpleado);
        return Response.ok("se modifico el empleado !!").build();
    }

    @Override
    protected GenericService getService() {
        return empleadoService;
    }
}
