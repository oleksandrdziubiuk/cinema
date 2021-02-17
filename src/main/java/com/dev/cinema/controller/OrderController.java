package com.dev.cinema.controller;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.service.dtomapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public void completeOrder(@RequestParam Long userId) {
        ShoppingCart byUser = cartService.getByUser(userService.get(userId));
        orderService.completeOrder(byUser);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersForUser(@RequestParam Long userId) {
        User user = userService.get(userId);
        List<Order> ordersHistory = orderService.getOrdersHistory(user);
        return ordersHistory.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }
}
