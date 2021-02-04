package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100);
        cinemaHall.setDescription("green hall");

        CinemaHallService cinemaHallService = (CinemaHallService)
                injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        System.out.println(cinemaHallService.getAll());

        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.now());

        MovieSessionService movieSessionService = (MovieSessionService)
                injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService
                .findAvailableSessions(movie.getId(), LocalDate.now()));

        AuthenticationService service = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        User bob = service.register("bob", "123");
        User alice = service.register("alice", "111");
        try {
            System.out.println(service.login("bob", "123"));
        } catch (AuthenticationException e) {
            System.out.println("Incorrect login");
        }

        ShoppingCartService shoppingCartService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);

        shoppingCartService.addSession(movieSession, bob);
        shoppingCartService.addSession(movieSession, alice);
        ShoppingCart byBob = shoppingCartService.getByUser(bob);
        ShoppingCart byAlice = shoppingCartService.getByUser(alice);
        System.out.println(byBob);
        System.out.println(byAlice);
        shoppingCartService.clear(byBob);
        shoppingCartService.clear(byAlice);
        System.out.println(shoppingCartService.getByUser(bob));
        System.out.println(shoppingCartService.getByUser(alice));

        shoppingCartService.addSession(movieSession, bob);
        OrderService orderService = (OrderService)
                injector.getInstance(OrderService.class);
        System.out.println(orderService.completeOrder(shoppingCartService.getByUser(bob)));
        System.out.println(shoppingCartService.getByUser(bob));
        System.out.println(orderService.getOrdersHistory(bob));

    }
}
