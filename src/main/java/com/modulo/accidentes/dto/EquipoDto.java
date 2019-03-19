package com.modulo.accidentes.dto;

import com.modulo.accidentes.model.Equipo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EquipoDto extends DtoBase<Equipo> {

    private String nombre;
    private String categoria;
    private Date fechaAdquisicion;
    private int cantidad;
    private String estado;
    private String image;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public EquipoDto toDto(Equipo equipo, ModelMapper mapper) {
        super.toDto(equipo, mapper);
        if (equipo.getImage() != null) {
            byte[] bytes = new byte[equipo.getImage().length];
            for (int i = 0; i < equipo.getImage().length; i++) {
                bytes[i] = equipo.getImage()[i];
            }
            String imageStr = Base64.encodeBase64String(bytes);
            setImage(imageStr);
        }
        //setFechaAdquisicion(toStringDate(equipo.getFechaAdquisicion()));
        return this;
    }

    private String toStringDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}
