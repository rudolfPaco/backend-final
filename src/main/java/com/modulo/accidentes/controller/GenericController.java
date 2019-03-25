package com.modulo.accidentes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modulo.accidentes.dto.DtoBase;
import com.modulo.accidentes.exception.InternalErrorException;
import com.modulo.accidentes.model.ModelBase;
import com.modulo.accidentes.service.GenericService;
import io.micrometer.core.instrument.util.IOUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class GenericController<E extends ModelBase, D extends DtoBase<E>> {

    protected static final String ID = "id";
    @Autowired
    protected ModelMapper modelMapper;
    @Autowired
    protected ObjectMapper objectMapper;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @OPTIONS
    public Response preflight() {
        Response.ResponseBuilder responseBuilder = Response.ok();
        responseBuilder.allow("OPTIONS").build();
        return responseBuilder.build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteElement(@PathParam("id") @NotNull Long id) {
        getService().deleteById(id);
    }

    @GET
    @Path("/{id}")
    public D getById(@PathParam("id") @NotNull Long id) {
        return toDto((E) (getService().findById(id)));
    }

    public List<D> getAll() {
        return toDto(getService().findAll());
    }

    public List<E> getAllE() {
        return getService().findAll();
    }

    @POST
    public D save(@RequestBody D element) {
        return toDto((E) getService().save(toModel(element)));
    }

    @PUT
    public D update(@RequestBody D element) {
        return toDto((E) getService().update(toModel(element)));
    }

    /**
     * Absence of key means skip setting value, and null means replace current value
     * by null If deep patch is needed take a look this reference:
     * https://stackoverflow.com/questions/17860520/spring-mvc-patch-method-partial-updates
     *
     * @param id      the id of the object to be patched
     * @param request
     * @return patched domain model
     */
    @PATCH
    @Path("/{id}")
    public D patch(@PathParam("id") Long id, HttpServletRequest request) {
        E domain = (E) (getService().findById(id));
        E updatedDomain;
        D dto;
        try {
            @SuppressWarnings("deprecation")
            String jsonString = IOUtils.toString(request.getInputStream());
            updatedDomain = objectMapper.readerForUpdating(domain).readValue(jsonString);
            dto = objectMapper.readValue(jsonString, getDtoClass());
        } catch (IOException e) {
            logger.error("Error reading request body.", e);
            throw new RuntimeException("Error reading request body.");
        }
        return toDto((E) getService().patch(dto, updatedDomain));
    }

    protected abstract GenericService getService();

    private D getInstanceOfD() {
        Class<D> type = getDtoClass();
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("No default constructor.", e);
        }
    }

    private E getInstanceOfE() {
        Class<E> type = getDomainClass();
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new InternalErrorException("No default constructor.", e);
        }
    }

    private Class<D> getDtoClass() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<D>) superClass.getActualTypeArguments()[1];
    }

    private Class<E> getDomainClass() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<E>) superClass.getActualTypeArguments()[0];
    }

    protected D toDto(E entity) {
        return (D) getInstanceOfD().toDto(entity, modelMapper);
    }

    protected E toModel(D dto) {
        return (E) getInstanceOfE().toDomain(dto, modelMapper);
    }

    protected List<D> toDto(List<E> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    protected List<E> toModel(List<D> dtos) {
        return dtos.stream().map(this::toModel).collect(Collectors.toList());
    }

    @Deprecated
    public <T> List<T> convertListToDtoList(List<?> elements, Class<T> classToConvert) {
        return elements.stream().map(element -> modelMapper.map(element, classToConvert)).collect(Collectors.toList());
    }
}
