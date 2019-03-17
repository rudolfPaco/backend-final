package com.modulo.accidentes.service;

import com.modulo.accidentes.model.Equipo;

import java.io.InputStream;

public interface EquipoService extends GenericService<Equipo> {
    void saveImage(Long id, InputStream file);
}
