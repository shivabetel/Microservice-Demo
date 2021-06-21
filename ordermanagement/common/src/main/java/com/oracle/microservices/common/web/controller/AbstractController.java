package com.oracle.microservices.common.web.controller;
import com.oracle.microservices.common.interfaces.IDto;
import com.oracle.microservices.common.interfaces.IEntity;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.search.ClientOperation;
import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class AbstractController<T extends IEntity, E extends Serializable,  S extends IDto> {

    @Autowired
    HttpServletRequest httpServletRequest;

    protected final S findByIdInternal(E id){
        return getService().findById(id)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
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
                    .fromEntityToDto(getService().create(getEntityToDtoMapper().fromDtoToEntity(dto)));
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

    protected  final List<S> searchAllInternal(Triple<String, ClientOperation, String>... constraints){
        return getService().searchAll(constraints)
                .stream()
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .collect(Collectors.toList());

    }

    protected  final S searchOneInternale(Triple<String, ClientOperation, String>... constraints){
        return getService().searchOne(constraints)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

    }




    protected final void updateInternal(E id, S dto){
      Optional<T> resource =  getService().findById(id);
      if(resource.isPresent()){
          getService().update( getEntityToDtoMapper().updateDtoToEntity(resource.get(), dto));

      }else{
          throw new ResourceNotFoundException("Resource not found");
      }

   }

   public abstract IService<T, E> getService();

    public abstract IEntityDtoMapper<T, S> getEntityToDtoMapper();


}
