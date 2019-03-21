package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.AccidenteDto;
import com.modulo.accidentes.model.Accidente;

public interface AccidenteService extends GenericService<Accidente> {
    void saveAccidente(AccidenteDto accidenteDto);

    void updateAccidente(AccidenteDto accidenteDto);
}
