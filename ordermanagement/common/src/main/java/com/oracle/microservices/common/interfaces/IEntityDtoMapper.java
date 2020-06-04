package com.oracle.microservices.common.interfaces;


public interface IEntityDtoMapper<T extends IEntity, E extends IDto> {

    E fromEntityToDto(T entity);

    T fromDtoToEntity(E dto);

    T updateDtoToEntity(T entity, E dto);


}
