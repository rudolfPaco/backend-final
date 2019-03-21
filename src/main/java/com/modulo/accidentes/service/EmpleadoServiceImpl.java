package com.modulo.accidentes.service;

import com.modulo.accidentes.model.Empleado;
import com.modulo.accidentes.repositories.EmpleadoRepository;
import com.modulo.accidentes.repositories.GenericRepository;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServiceImpl extends GenericServiceImpl<Empleado> implements EmpleadoService {

    private EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    protected GenericRepository<Empleado> getRepository() {
        return empleadoRepository;
    }
}
