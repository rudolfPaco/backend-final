package com.modulo.accidentes.dto;

import com.modulo.accidentes.model.Dotacion;
import org.modelmapper.ModelMapper;

import java.util.Date;

public class DotacionDto extends DtoBase<Dotacion> {

    private Date fechaAsignacion;
    private Date fechaDevolucion;
    private int cantidadAsignada;
    private String observacion;
    private long empleado_id;
    private long equipo_id;

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getCantidadAsignada() {
        return cantidadAsignada;
    }

    public void setCantidadAsignada(int cantidadAsignada) {
        this.cantidadAsignada = cantidadAsignada;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(long empleado_id) {
        this.empleado_id = empleado_id;
    }

    public long getEquipo_id() {
        return equipo_id;
    }

    public void setEquipo_id(long equipo_id) {
        this.equipo_id = equipo_id;
    }

    @Override
    public DotacionDto toDto(Dotacion dotacion, ModelMapper mapper) {
        super.toDto(dotacion, mapper);
        setEquipo_id(dotacion.getEquipoD().getId());
        setEmpleado_id(dotacion.getEmpleadoD().getId());
        return this;
    }
}
