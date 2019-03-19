package com.modulo.accidentes.service;

import com.modulo.accidentes.dto.DtoBase;
import com.modulo.accidentes.exception.NotFoundException;
import com.modulo.accidentes.model.ModelBase;
import com.modulo.accidentes.repositories.GenericRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@SuppressWarnings("rawtypes")
public abstract class GenericServiceImpl<T extends ModelBase> implements GenericService<T> {

    @Autowired
    protected ModelMapper modelMapper;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<T> findAll() {
        return getRepository().findAll();
    }

    @Override
    public T findById(Long id) {
        final Optional<T> optional = getRepository().findById(id);
        if (!optional.isPresent()) {
            String typeName = (((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
                    .getTypeName();
            typeName = typeName.substring(typeName.lastIndexOf('.') + 1);
            throw new NotFoundException(String.format("%s Not found with id %s", typeName, id));
        } else {
            return optional.get();
        }
    }

    @Override
    public T save(T model) {
        validateSave(model);
        T t = getRepository().save(model);
        return findById(t.getId());
    }

    @Override
    public T update(T model) {
        validateSave(model);
        T t = getRepository().saveAndFlush(model);
        return findById(t.getId());
    }

    @Override
    public T patch(DtoBase dto, T model) {
        processDtoToDomainPatch(dto, model);
        return save(model);
    }

    protected void processDtoToDomainPatch(DtoBase dto, T updatedDomain) {

    }

    protected void validateSave(T model) {

    }

    @Override
    public List<T> saveAll(Iterable<T> models) {
        return StreamSupport.stream(models.spliterator(), false).map(this::save).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    @Override
    public Byte[] getBytes(MultipartFile file) {
        try {
            Byte[] bytes = new Byte[file.getBytes().length];
            int i = 0;
            for (Byte aByte : file.getBytes()) {
                bytes[i++] = aByte;
            }
            return bytes;
        } catch (IOException e) {
            logger.error("Error reading file", e);
        }
        return new Byte[0];
    }

    protected <E extends ModelBase> void appendModel(E model, Set<E> modelSet) {
        if (model != null) {
            modelSet.add(model);
        }
    }

    protected abstract GenericRepository<T> getRepository();
}
