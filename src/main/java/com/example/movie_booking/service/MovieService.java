package com.example.movie_booking.service;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie
    getMovieByTitle(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NoSuchElementException("Movie not found"));
    }
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);

    }
}
