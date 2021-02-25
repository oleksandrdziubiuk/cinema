package com.dev.theatre.service.impl;

import com.dev.theatre.dao.ShoppingCartDao;
import com.dev.theatre.dao.TicketDao;
import com.dev.theatre.model.PerformanceSession;
import com.dev.theatre.model.ShoppingCart;
import com.dev.theatre.model.Ticket;
import com.dev.theatre.model.User;
import com.dev.theatre.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final TicketDao ticketDao;
    private final ShoppingCartDao cartDao;

    public ShoppingCartServiceImpl(TicketDao ticketDao, ShoppingCartDao cartDao) {
        this.ticketDao = ticketDao;
        this.cartDao = cartDao;
    }

    @Override
    public void addSession(PerformanceSession performanceSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setPerformanceSession(performanceSession);
        ticket.setUser(user);
        ticketDao.add(ticket);
        ShoppingCart shoppingCart = cartDao.getByUser(user);
        shoppingCart.getTickets().add(ticket);
        cartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return cartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        cartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().clear();
        cartDao.update(shoppingCart);
    }
}
