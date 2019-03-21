package com.modulo.accidentes.controller;

import com.modulo.accidentes.dto.EquipoDto;
import com.modulo.accidentes.model.Equipo;
import com.modulo.accidentes.service.EquipoService;
import com.modulo.accidentes.service.GenericService;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@Path("/equipos")
@Produces(MediaType.APPLICATION_JSON)
public class EquipoController extends GenericController<Equipo, EquipoDto> {

    private EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @Override
    @GET
    public List<EquipoDto> getAll() {
        return super.getAll();
    }

    @Override
    protected GenericService getService() {
        return equipoService;
    }
}
