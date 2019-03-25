package com.modulo.accidentes.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "empleado")
public class Empleado extends ModelBase implements Serializable {

    private String nombre;
    private String apellido;
    private String cargo;
    private String email;
    private Date fechaContratacion;

    //un empleado puede tener muchos accidentes
    @OneToMany(mappedBy = "empleadoA", cascade = CascadeType.ALL)
    private List<Accidente> accidentes = new ArrayList<>();

    //un empleado puede tener muchas dotaciones
    @OneToMany(mappedBy = "empleadoD", cascade = CascadeType.ALL)
    private List<Dotacion> dotaciones = new ArrayList<>();


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public List<Accidente> getAccidentes() {
        return accidentes;
    }

    public void setAccidentes(List<Accidente> accidentes) {
        this.accidentes = accidentes;
    }

    public List<Dotacion> getDotaciones() {
        return dotaciones;
    }

    public void setDotaciones(List<Dotacion> dotaciones) {
        this.dotaciones = dotaciones;
    }
}
