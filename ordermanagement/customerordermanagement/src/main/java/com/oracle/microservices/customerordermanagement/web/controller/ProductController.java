package com.oracle.microservices.customerordermanagement.web.controller;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;
import com.oracle.microservices.customerordermanagement.service.IProductService;
import com.oracle.microservices.customerordermanagement.web.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractController<Product, Long, ProductDTO> {

    private IProductService service;
    private IEntityDtoMapper<Product, ProductDTO> entityDtoMapper;

    public ProductController(IProductService service, IEntityDtoMapper<Product, ProductDTO> entityDtoMapper) {
        this.service = service;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return super.findAllInternal();
    }

    @PostMapping
    public void create(@RequestBody ProductDTO productDTO){
        super.createOneInternal(productDTO);
    }

    @Override
    public IProductService getService() {
        return service;
    }

    @Override
    public IEntityDtoMapper<Product, ProductDTO> getEntityToDtoMapper() {
        return entityDtoMapper;
    }
}
