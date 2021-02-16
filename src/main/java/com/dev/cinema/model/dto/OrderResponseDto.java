package com.dev.cinema.model.dto;

import java.util.List;

public class OrderResponseDto {
    private Long id;
    private List<Long> ticketId;
    private String orderDate;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Long> ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
