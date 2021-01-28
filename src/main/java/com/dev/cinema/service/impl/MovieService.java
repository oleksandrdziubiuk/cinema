package com.dev.cinema.service.impl;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
