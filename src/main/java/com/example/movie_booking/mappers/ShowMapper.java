package com.example.movie_booking.mappers;

import com.example.movie_booking.dto.CreateShowRequestDTO;
import com.example.movie_booking.dto.ShowDTO;
import com.example.movie_booking.model.Movie;
import com.example.movie_booking.model.Screen;
import com.example.movie_booking.model.Show;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {

    public ShowDTO toDTO(Show show) {
        return new ShowDTO(
                show.getId(),
                show.getMovie().getId(),
                show.getScreen().getId(),
                show.getStartTime(),
                show.getEndTime()
        );
    }

    public Show toEntity(CreateShowRequestDTO request, Movie movie, Screen screen) {
        return new Show(null, movie, screen, request.getStartTime(), request.getEndTime());
    }
}
