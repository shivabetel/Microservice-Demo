package com.oracle.microservices.customerordermanagement.web.mappers;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.customerordermanagement.persistence.dao.IProductJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.dao.IShippingAddressJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.dao.IUserJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.Order;
import com.oracle.microservices.customerordermanagement.persistence.model.OrderDetails;
import com.oracle.microservices.customerordermanagement.persistence.model.ShippingAddress;
import com.oracle.microservices.customerordermanagement.web.dtos.OrderDTO;
import com.oracle.microservices.customerordermanagement.web.dtos.OrderDetailsDTO;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;


@Component
public class OrderEntityDtoMapper implements IEntityDtoMapper<Order, OrderDTO> {

    private IEntityDtoMapper<OrderDetails, OrderDetailsDTO> orderDtlEntityDtoMapper;

    private IUserJpaDao userJpaDao;

    private IProductJpaDao productJpaDao;

    private IShippingAddressJpaDao shippingAddressJpaDao;


    public OrderEntityDtoMapper(IEntityDtoMapper<OrderDetails, OrderDetailsDTO> orderDtlEntityDtoMapper, IUserJpaDao userJpaDao,
                                IShippingAddressJpaDao shippingAddressJpaDao) {
        this.orderDtlEntityDtoMapper = orderDtlEntityDtoMapper;
        this.userJpaDao = userJpaDao;
        this.shippingAddressJpaDao = shippingAddressJpaDao;
    }

    @Override
    public OrderDTO fromEntityToDto(Order entity) {
        ShippingAddress shippingAddress = entity.getShippingAddress();
        AddressDTO addressDTO = new AddressDTO();
        if (shippingAddress != null) {
            addressDTO.setAddressLine1(shippingAddress.getAddressLine1());
            addressDTO.setAddressLine2(shippingAddress.getAddressLine2());
            addressDTO.setCity(shippingAddress.getCity());
            addressDTO.setState(shippingAddress.getState());
            addressDTO.setPincode(shippingAddress.getPincode());
        }
        return new OrderDTO(entity.getId().toString(),
                entity.getOrderDate().toString(),
                entity.getOrderStatus(),
                entity.getDetails()
                        .stream()
                        .map(orderDtlEntityDtoMapper::fromEntityToDto)
                        .collect(Collectors.toList()),
                addressDTO
        );
    }

    @Override
    public Order fromDtoToEntity(OrderDTO dto) {
        try {
            Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getOrderDate());
            Order order = new Order(orderDate,
                    "ORDERED",
                    userJpaDao.findById(Long.valueOf(dto.getCustomerId()))
                            .orElse(null),
                    shippingAddressJpaDao.findById(Long.valueOf(dto.getCustomerId()), Long.valueOf(dto.getAddress().getAddressId()))
                            .orElse(null));
            order.setDetails(dto.getOrderDetails()
                    .stream()
                    .map(orderDetailsDTO -> this.mapOrderDtlsDtoToEntity(order, orderDetailsDTO))
                    .collect(Collectors.toList()));

            return order;
        } catch (ParseException ex) {
            throw new RuntimeException("Error while converting Order Date");
        }


    }

    @Override
    public Order updateDtoToEntity(Order entity, OrderDTO dto) {
        return null;
    }

    private OrderDetails mapOrderDtlsDtoToEntity(Order order, OrderDetailsDTO dto) {
        OrderDetails orderDetails = orderDtlEntityDtoMapper.fromDtoToEntity(dto);
        orderDetails.setOrder(order);
        return orderDetails;
    }
}
