package com.modulo.accidentes.controller;

import com.modulo.accidentes.dto.DotacionDto;
import com.modulo.accidentes.model.Dotacion;
import com.modulo.accidentes.service.DotacionService;
import com.modulo.accidentes.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/dotaciones")
@Produces(MediaType.APPLICATION_JSON)
public class DotacionController extends GenericController<Dotacion, DotacionDto> {

    private DotacionService dotacionService;

    public DotacionController(DotacionService dotacionService) {
        this.dotacionService = dotacionService;
    }

    @Path("/all")
    @GET
    public List<DotacionDto> getAllDotaciones() {
        return dotacionService.getAllDotaciones();
    }

    @Override
    @GET
    public List<DotacionDto> getAll() {
        return super.getAll();
    }

    @Path("/create")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@RequestBody DotacionDto dotacionDto) {
        dotacionService.saveDotacion(dotacionDto);
        return Response.ok("se creo una nueva dotacion de equipo a empleado !!").build();
    }

    @Path("/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@RequestBody DotacionDto dotacionDto) {
        dotacionService.updateDotacion(dotacionDto);
        return Response.ok("se modifico los datos de la dotacion !!").build();
    }

    @Override
    protected GenericService getService() {
        return dotacionService;
    }
}
