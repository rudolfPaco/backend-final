package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.DotacionDto;
import com.modulo.accidentes.model.Dotacion;

import java.util.List;

public interface DotacionService extends GenericService<Dotacion> {
    void saveDotacion(DotacionDto dotacionDto);

    void updateDotacion(DotacionDto dotacionDto);

    List<DotacionDto> getAllDotaciones();
}
