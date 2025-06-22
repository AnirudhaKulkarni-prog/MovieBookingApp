package com.example.movie_booking.exception;

public class OverlappingShowsException extends RuntimeException{
    public OverlappingShowsException(String message) {
        super(message);
    }
}
