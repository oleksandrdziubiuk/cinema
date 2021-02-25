package com.dev.theatre.service.dtomapper;

import com.dev.theatre.model.Order;
import com.dev.theatre.model.Ticket;
import com.dev.theatre.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(order.getId());
        responseDto.setTicketId(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        responseDto.setOrderDate(order.getOrderDate());
        responseDto.setUserId(order.getUser().getId());
        return responseDto;
    }
}
