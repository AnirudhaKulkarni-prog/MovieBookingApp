package com.example.movie_booking.mappers;


import com.example.movie_booking.dto.ScreenDTO;
import com.example.movie_booking.dto.TheatreDTO;
import com.example.movie_booking.model.Screen;
import com.example.movie_booking.model.Theatre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TheatreMapper {

    private final ScreenMapper screenMapper;

    public TheatreMapper(ScreenMapper screenMapper) {
        this.screenMapper = screenMapper;
    }

    public TheatreDTO toDto(Theatre theatre)
    {
        TheatreDTO theatreDTO = new TheatreDTO();
        theatreDTO.setId(theatre.getId());
        theatreDTO.setCity(theatre.getCity());
        theatreDTO.setName(theatre.getName());

        if(theatre.getScreens() !=null) {
            List<ScreenDTO> screenDTOList = theatre.getScreens().stream().map(x -> screenMapper.toDTO(x)).collect(Collectors.toList());
            theatreDTO.setScreens(screenDTOList);
        }

        return theatreDTO;
    }

    public Theatre toEntity(TheatreDTO theaterDTO)
    {
        Theatre theatre = new Theatre();
        theatre.setId(theaterDTO.getId());
        theatre.setName(theaterDTO.getName());
        theatre.setCity(theaterDTO.getCity());
        return theatre;

    }

}
