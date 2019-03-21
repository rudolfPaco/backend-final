package com.modulo.accidentes.dto;

import com.modulo.accidentes.model.Empleado;

import java.util.Calendar;
import java.util.Date;

public class EmpleadoDto extends DtoBase<Empleado> {
    private String nombre;
    private String cargo;
    private String email;
    private Date fechaContratacion;
    private int antiguedad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getAntiguedad() {
        return calcularAntiguedad(fechaContratacion);
    }

    public static int calcularAntiguedad(Date fecha) {
        Calendar cal = Calendar.getInstance(); // current date
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(fecha); // now born date
        int years = currYear - cal.get(Calendar.YEAR);
        int bornMonth = cal.get(Calendar.MONTH);

        if (bornMonth == currMonth) { // same month
            return cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years : years - 1;
        } else {
            return bornMonth < currMonth ? years - 1 : years;
        }
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
