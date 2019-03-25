package com.modulo.accidentes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "accidente")
public class Accidente extends ModelBase implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    private String lugar;
    private String tipo;
    private String descripcion;
    private String estado;

    //muchos accidentes puede tener un empleadoA
    @ManyToOne
    @JoinColumn(name = "empleado_id", insertable = true)
    @JsonIgnore
    private Empleado empleadoA;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getEmpleadoA() {
        return empleadoA;
    }

    public void setEmpleadoA(Empleado empleadoA) {
        this.empleadoA = empleadoA;
    }
}
