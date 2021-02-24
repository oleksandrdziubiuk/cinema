package com.dev.theatre.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> ticketId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Long> ticketId) {
        this.ticketId = ticketId;
    }
}
