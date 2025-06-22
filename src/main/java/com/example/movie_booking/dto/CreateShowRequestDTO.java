package com.example.movie_booking.dto;

import java.time.LocalDateTime;

public class CreateShowRequestDTO {
    private Long movieId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getScreenId() {
        return screenId;
    }

    public void setScreenId(Long screenId) {
        this.screenId = screenId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public CreateShowRequestDTO(Long movieId, Long screenId, LocalDateTime startTime, LocalDateTime endTime) {
        this.movieId = movieId;
        this.screenId = screenId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private Long screenId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
