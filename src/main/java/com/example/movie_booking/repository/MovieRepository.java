package com.example.movie_booking.repository;

import com.example.movie_booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

    @Query("SELECT m FROM Movie m WHERE m.rating > 4")
    List<Movie> findRatedHigherThanFour();
}