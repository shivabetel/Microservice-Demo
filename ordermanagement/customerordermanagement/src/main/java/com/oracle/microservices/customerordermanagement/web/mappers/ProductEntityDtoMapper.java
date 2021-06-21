package com.oracle.microservices.customerordermanagement.web.mappers;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.customerordermanagement.persistence.dao.IProductCategoryJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;
import com.oracle.microservices.customerordermanagement.web.dtos.ProductDTO;
import org.springframework.stereotype.Component;


@Component
public class ProductEntityDtoMapper implements IEntityDtoMapper<Product, ProductDTO> {

    private IProductCategoryJpaDao productCategoryJpaDao;

    public ProductEntityDtoMapper(IProductCategoryJpaDao productCategoryJpaDao) {
        this.productCategoryJpaDao = productCategoryJpaDao;
    }

    @Override
    public ProductDTO fromEntityToDto(Product entity) {
        return new ProductDTO(entity.getId().toString(),
                              entity.getName(),
                              entity.getDescription(),
                              "http://localhost/images/"+entity.getId()+".png",
                              entity.getCategory().getId(),
                              String.valueOf(entity.getPrice()));
    }

    @Override
    public Product fromDtoToEntity(ProductDTO dto) {
        return new Product(dto.getProductName(),
                          dto.getDescription(),
                          true,
                          null,
                           productCategoryJpaDao.findById(dto.getCategory())
                                                .orElse(null));
    }

    @Override
    public Product updateDtoToEntity(Product entity, ProductDTO dto) {
        return null;
    }
}
