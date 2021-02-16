package com.dev.cinema.service.dtomapper;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.OrderResponseDto;
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
        responseDto.setOrderDate(order.getOrderDate().toString());
        responseDto.setUserId(order.getUser().getId());
        return responseDto;
    }
}
