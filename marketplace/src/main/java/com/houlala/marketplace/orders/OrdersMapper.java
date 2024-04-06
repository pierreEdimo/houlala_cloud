package com.houlala.marketplace.orders;

import org.mapstruct.Mapper;

import com.houlala.marketplace.location.Location;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrdersMapper {

    @Mapping(target = "id", source = "order.id")
    OrderDto toOrderDto(Order order, Location location);
}
