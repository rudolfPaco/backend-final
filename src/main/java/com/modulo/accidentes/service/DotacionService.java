package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.DotacionDto;
import com.modulo.accidentes.model.Dotacion;

public interface DotacionService extends GenericService<Dotacion> {
    void saveDotacion(DotacionDto dotacionDto);

    void updateDotacion(DotacionDto dotacionDto);
}
