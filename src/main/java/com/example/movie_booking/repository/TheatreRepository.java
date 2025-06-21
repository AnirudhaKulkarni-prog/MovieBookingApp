package com.example.movie_booking.repository;

import com.example.movie_booking.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    List<Theatre> findByCity(String city);
}
