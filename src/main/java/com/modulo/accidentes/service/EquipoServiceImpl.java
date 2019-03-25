package com.modulo.accidentes.service;

import com.modulo.accidentes.model.Equipo;
import com.modulo.accidentes.repositories.EquipoRepository;
import com.modulo.accidentes.repositories.GenericRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@Service
public class EquipoServiceImpl extends GenericServiceImpl<Equipo> implements EquipoService {

    private EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public void saveImage(Long id, InputStream file) {
        Equipo itemPersisted = findById(id);
        try {
            Byte[] bytes = ImageUtils.inputStreamToByteArray(file);
            itemPersisted.setImage(bytes);
            getRepository().save(itemPersisted);
        } catch (IOException e) {
            logger.error("Error reading file", e);
            e.printStackTrace();
        }
    }

    @Override
    protected GenericRepository<Equipo> getRepository() {
        return equipoRepository;
    }
}
