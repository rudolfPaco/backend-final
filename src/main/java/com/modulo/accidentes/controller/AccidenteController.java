package com.modulo.accidentes.controller;

import com.modulo.accidentes.dto.AccidenteDto;
import com.modulo.accidentes.model.Accidente;
import com.modulo.accidentes.service.AccidenteService;
import com.modulo.accidentes.service.GenericService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/accidentes")
@Produces(MediaType.APPLICATION_JSON)
public class AccidenteController extends GenericController<Accidente, AccidenteDto> {

    private AccidenteService accidenteService;

    public AccidenteController(AccidenteService accidenteService) {
        this.accidenteService = accidenteService;
    }

    @Override
    @GET
    public List<AccidenteDto> getAll() {
        return super.getAll();
    }

    @Path("/create")
    @POST
    public Response create(@RequestBody AccidenteDto accidenteDto) {
        accidenteService.saveAccidente(accidenteDto);
        return Response.ok("se registro un nuevo accidente !!").build();
    }

    @Path("/update")
    @PUT
    public Response actualizar(@RequestBody AccidenteDto accidenteDto) {
        accidenteService.updateAccidente(accidenteDto);
        return Response.ok("se actualizo los datos del accidente !!").build();
    }


    @Override
    protected GenericService getService() {
        return accidenteService;
    }
}
