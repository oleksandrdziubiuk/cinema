package com.dev.cinema;

import com.dev.cinema.exception.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
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

        User user = new User();
        user.setEmail("bob");
        user.setPassword("123");

        AuthenticationService service = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        System.out.println(service.registration(user.getEmail(), user.getPassword()));
        try {
            System.out.println(service.login(user.getEmail(), user.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println("Incorrect login");
        }
    }
}
