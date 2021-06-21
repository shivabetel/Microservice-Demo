package com.oracle.microservices.customerordermanagement.web.mappers;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.customerordermanagement.persistence.dao.IProductJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.OrderDetails;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;
import com.oracle.microservices.customerordermanagement.web.dtos.OrderDetailsDTO;
import org.springframework.stereotype.Component;


@Component
public class OrderDetailsEntityDtoMapper implements IEntityDtoMapper<OrderDetails, OrderDetailsDTO> {

    private IProductJpaDao productJpaDao;

    private ProductEntityDtoMapper productEntityDtoMapper;


    public OrderDetailsEntityDtoMapper(IProductJpaDao productJpaDao, ProductEntityDtoMapper productEntityDtoMapper) {
        this.productJpaDao = productJpaDao;
        this.productEntityDtoMapper = productEntityDtoMapper;
    }

    @Override
    public OrderDetailsDTO fromEntityToDto(OrderDetails entity) {
        return new OrderDetailsDTO(entity.getId().toString(),
                                   productEntityDtoMapper.fromEntityToDto(entity.getProduct()),
                                   entity.getQty(),
                                   entity.getTotalPrice());
    }

    @Override
    public OrderDetails fromDtoToEntity(OrderDetailsDTO dto) {
        Product product = productJpaDao.findById(Long.valueOf(dto.getProduct().getProductId()))
                .orElse(null);
        return new OrderDetails(product,
                               dto.getQty(),
                               product != null ? Float.valueOf(product.getPrice() * dto.getQty()) : 0);
    }

    @Override
    public OrderDetails updateDtoToEntity(OrderDetails entity, OrderDetailsDTO dto) {
        return null;
    }
}
