package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.AccidenteDto;
import com.modulo.accidentes.model.Accidente;
import com.modulo.accidentes.repositories.AccidenteRepository;
import com.modulo.accidentes.repositories.EmpleadoRepository;
import com.modulo.accidentes.repositories.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class AccidenteServiceImpl extends GenericServiceImpl<Accidente> implements AccidenteService {

    private AccidenteRepository accidenteRepository;
    private EmpleadoRepository empleadoRepository;

    public AccidenteServiceImpl(AccidenteRepository accidenteRepository, EmpleadoRepository empleadoRepository) {
        this.accidenteRepository = accidenteRepository;
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    protected GenericRepository<Accidente> getRepository() {
        return accidenteRepository;
    }

    @Override
    public void saveAccidente(AccidenteDto accidenteDto) {
        Accidente accidente = new Accidente();
        accidente.setDescripcion(accidenteDto.getDescripcion());
        accidente.setEstado(accidenteDto.getEstado());
        accidente.setFecha(accidenteDto.getFecha());
        accidente.setLugar(accidenteDto.getLugar());
        accidente.setTipo(accidenteDto.getTipo());
        accidente.setEmpleadoA(empleadoRepository.getOne(accidenteDto.getEmpleado_id()));
        accidenteRepository.save(accidente);
    }

    @Override
    public void updateAccidente(AccidenteDto accidenteDto) {
        Accidente accidente = new Accidente();
        accidente.setId(accidenteDto.getId());
        accidente.setDescripcion(accidenteDto.getDescripcion());
        accidente.setEstado(accidenteDto.getEstado());
        accidente.setFecha(accidenteDto.getFecha());
        accidente.setLugar(accidenteDto.getLugar());
        accidente.setTipo(accidenteDto.getTipo());
        accidente.setEmpleadoA(empleadoRepository.getOne(accidenteDto.getEmpleado_id()));
        accidenteRepository.saveAndFlush(accidente);
    }
}
