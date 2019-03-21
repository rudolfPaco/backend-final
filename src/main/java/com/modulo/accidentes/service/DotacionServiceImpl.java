package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.DotacionDto;
import com.modulo.accidentes.model.Dotacion;
import com.modulo.accidentes.repositories.DotacionRepository;
import com.modulo.accidentes.repositories.EmpleadoRepository;
import com.modulo.accidentes.repositories.EquipoRepository;
import com.modulo.accidentes.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class DotacionServiceImpl extends GenericServiceImpl<Dotacion> implements DotacionService {

    private EquipoRepository equipoRepository;
    private DotacionRepository dotacionRepository;
    private EmpleadoRepository empleadoRepository;

    public DotacionServiceImpl(EquipoRepository equipoRepository, DotacionRepository dotacionRepository, EmpleadoRepository empleadoRepository) {
        this.equipoRepository = equipoRepository;
        this.dotacionRepository = dotacionRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    protected GenericRepository getRepository() {
        return dotacionRepository;
    }

    @Override
    public void saveDotacion(DotacionDto dotacionDto) {
        Dotacion dotacion = new Dotacion();
        dotacion.setFechaAsignacion(dotacionDto.getFechaAsignacion());
        dotacion.setFechaDevolucion(dotacionDto.getFechaDevolucion());
        dotacion.setCantidadAsignada(dotacionDto.getCantidadAsignada());
        dotacion.setObservacion(dotacionDto.getObservacion());
        dotacion.setEmpleadoD(empleadoRepository.getOne(dotacionDto.getEmpleado_id()));
        dotacion.setEquipoD(equipoRepository.getOne(dotacionDto.getEquipo_id()));
        dotacionRepository.save(dotacion);
    }

    @Override
    public void updateDotacion(DotacionDto dotacionDto) {

        Dotacion dotacion = new Dotacion();
        dotacion.setId(dotacionDto.getId());
        dotacion.setFechaAsignacion(dotacionDto.getFechaAsignacion());
        dotacion.setFechaDevolucion(dotacionDto.getFechaDevolucion());
        dotacion.setCantidadAsignada(dotacionDto.getCantidadAsignada());
        dotacion.setObservacion(dotacionDto.getObservacion());
        dotacion.setEmpleadoD(empleadoRepository.getOne(dotacionDto.getEmpleado_id()));
        dotacion.setEquipoD(equipoRepository.getOne(dotacionDto.getEquipo_id()));
        dotacionRepository.saveAndFlush(dotacion);
    }
}
