package com.modulo.accidentes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "dotacion")
public class Dotacion extends ModelBase implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDevolucion;
    private int cantidadAsignada;
    private String observacion;

    //muchos dotaciones puede tener un empleadoD
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    @JsonIgnore
    private Empleado empleadoD;

    //muchas dotaciones puede realizar un equipo
    @ManyToOne
    @JoinColumn(name = "equipo_id")
    @JsonIgnore
    private Equipo equipoD;

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

    public Empleado getEmpleadoD() {
        return empleadoD;
    }

    public void setEmpleadoD(Empleado empleadoD) {
        this.empleadoD = empleadoD;
    }

    public Equipo getEquipoD() {
        return equipoD;
    }

    public void setEquipoD(Equipo equipoD) {
        this.equipoD = equipoD;
    }
}
