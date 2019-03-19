package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.DtoBase;
import com.modulo.accidentes.model.ModelBase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface GenericService<T extends ModelBase> {
    List<T> findAll();

    T findById(Long id);

    T save(T model);

    T update(T model);

    T patch(DtoBase dto, T model);

    List<T> saveAll(Iterable<T> models);

    void deleteById(Long id);

    Byte[] getBytes(MultipartFile file);

}
