package com.dev.theatre.controller;

import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.User;
import com.dev.theatre.model.dto.ShoppingCartResponseDto;
import com.dev.theatre.service.PerformanceSessionService;
import com.dev.theatre.service.ShoppingCartService;
import com.dev.theatre.service.UserService;
import com.dev.theatre.service.dtomapper.ShoppingCartMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final PerformanceSessionService performanceSessionService;
    private final UserService userService;
    private final ShoppingCartMapper shoppingCartMapper;

    public ShoppingCartController(ShoppingCartService cartService,
                                  PerformanceSessionService performanceSessionService,
                                  UserService userService, ShoppingCartMapper shoppingCartMapper) {
        this.cartService = cartService;
        this.performanceSessionService = performanceSessionService;
        this.userService = userService;
        this.shoppingCartMapper = shoppingCartMapper;
    }

    @PostMapping("/performance-sessions")
    public void addPerformanceSession(Authentication authentication,
                                      @RequestParam Long performanceSessionId) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).get();
        PerformanceSession session = performanceSessionService.get(performanceSessionId);
        cartService.addSession(session, user);
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        User user = userService.findByEmail(email).get();
        return shoppingCartMapper.toDto(cartService.getByUser(user));
    }
}
