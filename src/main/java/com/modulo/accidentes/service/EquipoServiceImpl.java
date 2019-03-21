package com.modulo.accidentes.service;

import com.modulo.accidentes.model.Equipo;
import com.modulo.accidentes.repositories.EquipoRepository;
import com.modulo.accidentes.repositories.GenericRepository;
import org.springframework.stereotype.Service;


@Service
public class EquipoServiceImpl extends GenericServiceImpl<Equipo> implements EquipoService {

    private EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    protected GenericRepository<Equipo> getRepository() {
        return equipoRepository;
    }
}
