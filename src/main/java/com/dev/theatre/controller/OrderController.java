package com.dev.theatre.controller;

import com.dev.theatre.model.User;
import com.dev.theatre.model.dto.OrderResponseDto;
import com.dev.theatre.service.OrderService;
import com.dev.theatre.service.ShoppingCartService;
import com.dev.theatre.service.UserService;
import com.dev.theatre.service.dtomapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShoppingCartService cartService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(ShoppingCartService cartService, UserService userService,
                           OrderService orderService, OrderMapper orderMapper) {
        this.cartService = cartService;
        this.userService = userService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).get();
        orderService.completeOrder(cartService.getByUser(user));
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersForUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).get();
        return orderService.getOrdersHistory(user).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
