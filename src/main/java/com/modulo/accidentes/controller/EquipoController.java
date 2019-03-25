package com.modulo.accidentes.controller;

import com.modulo.accidentes.dto.EquipoDto;
import com.modulo.accidentes.model.Equipo;
import com.modulo.accidentes.service.EquipoService;
import com.modulo.accidentes.service.GenericService;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
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

    @Path("/{id}/image")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@PathParam("id") String id,
                               @FormDataParam("file") InputStream file,
                               @FormDataParam("file") FormDataContentDisposition fileDisposition) {
        equipoService.saveImage(Long.valueOf(id), file);
        return Response.ok("se ha cargado la imagen correctamente !!").build();
    }

    @Override
    protected GenericService getService() {
        return equipoService;
    }
}
