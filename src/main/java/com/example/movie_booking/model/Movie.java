package com.example.movie_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data

@Builder
public class Movie {

    public Movie() {
    }

    public Movie(Long id, String title, String genre, String language, int durationInMinutes, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.durationInMinutes = durationInMinutes;
        this.rating = rating;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private String language;
    private int durationInMinutes;
    private double rating;
}