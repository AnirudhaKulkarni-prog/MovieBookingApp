package com.example.movie_booking.mappers;

import com.example.movie_booking.dto.ScreenDTO;
import com.example.movie_booking.model.Screen;
import org.springframework.stereotype.Component;


@Component
public class ScreenMapper {

    public ScreenDTO toDTO(Screen screen)
    {
        ScreenDTO screenDTO = new ScreenDTO();
        screenDTO.setId(screen.getId());
        screenDTO.setName(screen.getName());

        return screenDTO;

    }

    public Screen toEntity(ScreenDTO screenDTO)
    {
        Screen screen = new Screen();
        screen.setId(screenDTO.getId());
        screen.setName(screenDTO.getName());

        return screen;
    }
}
