package com.oracle.microservices.customerordermanagement.web.controller;


import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.customerordermanagement.persistence.model.Order;
import com.oracle.microservices.customerordermanagement.service.IOrderService;
import com.oracle.microservices.customerordermanagement.web.dtos.OrderDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController<Order, Long, OrderDTO> {

    private IOrderService orderService;

    private IEntityDtoMapper<Order, OrderDTO> entityDtoMapper;


    public OrderController(IOrderService orderService, IEntityDtoMapper<Order, OrderDTO> entityDtoMapper) {
        this.orderService = orderService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping("/{customerId}")
    List<OrderDTO> findOrdersByCustomerId(@PathVariable("customerId") String customerId){
       return getService().findOrdersByCustomerId(Long.valueOf(customerId))
                    .stream()
                     .map(getEntityToDtoMapper()::fromEntityToDto)
                     .collect(Collectors.toList());
    }


    @PostMapping("/{customerId}")
     public OrderDTO create(@RequestBody OrderDTO orderDTO, @PathVariable("customerId") String customerId){
        orderDTO.setCustomerId(customerId);
        return super.createOneInternal(orderDTO);
     }

    @Override
    public IOrderService getService() {
        return orderService;
    }

    @Override
    public IEntityDtoMapper<Order, OrderDTO> getEntityToDtoMapper() {
        return entityDtoMapper;
    }
}
