package com.dev.cinema.dao.impl;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
