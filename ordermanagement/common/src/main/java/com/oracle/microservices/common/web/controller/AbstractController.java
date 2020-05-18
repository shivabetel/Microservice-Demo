package com.oracle.microservices.common.web.controller;
import com.oracle.microservices.common.interfaces.IDto;
import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.service.interfaces.IService;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends IEntity, E extends Serializable,  S extends IDto> {

    protected final S findByIdInternal(E id){
        return getService().findById(id).map(getEntityToDtoMapper()::fromEntityToDto).orElseGet(null);
    }

    protected  final List<S> findAllInternal(){
        return getService().findAll()
                .stream()
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .collect(Collectors.toList());
    }

    protected final S createOneInternal(S dto){
        if(dto != null) {
            return getEntityToDtoMapper()
                    .fromEntityToDto(getService().save(getEntityToDtoMapper().fromDtoToEntity(dto)));
        }

        return null;
    }

    protected final List<S> createAllInternal(List<S> dtos){
        if(dtos != null && !dtos.isEmpty()){
           return getService().saveAll(dtos.stream()
                   .map(getEntityToDtoMapper()::fromDtoToEntity)
                   .collect(Collectors.toList()))
                   .stream().map(getEntityToDtoMapper()::fromEntityToDto)
                   .collect(Collectors.toList());
        }
        return Arrays.asList();
    }

   public abstract IService<T, E> getService();

    public abstract IEntityDtoMapper<T, S> getEntityToDtoMapper();


}
