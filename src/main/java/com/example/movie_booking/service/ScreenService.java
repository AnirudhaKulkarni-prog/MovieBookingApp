package com.example.movie_booking.service;

import com.example.movie_booking.dto.ScreenDTO;
import com.example.movie_booking.mappers.ScreenMapper;
import com.example.movie_booking.model.Screen;
import com.example.movie_booking.model.Theatre;
import com.example.movie_booking.repository.ScreenRepository;
import com.example.movie_booking.repository.TheatreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    private final ScreenMapper screenMapper;

    public ScreenService(ScreenRepository screenRepository, TheatreRepository theatreRepository, ScreenMapper screenMapper) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
        this.screenMapper = screenMapper;
    }

    public List<ScreenDTO> getAll() {
        List<Screen> screenList = screenRepository.findAll();

        List<ScreenDTO> screenDTOList=screenList.stream().map(screenMapper::toDTO).collect(Collectors.toList());

        return screenDTOList;
    }

    public ScreenDTO save(Screen screen, Long theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(() -> new NoSuchElementException("Theatre not found"));
        screen.setTheatre(theatre);
        Screen savedScreen= screenRepository.save(screen);

        return screenMapper.toDTO(screen);
    }

    public List<ScreenDTO> getScreensByTheatre(Long theatreId) {
        List<Screen> listOfScreensByTheatre=screenRepository.findByTheatreId(theatreId);



        List<ScreenDTO> screenDTOList = listOfScreensByTheatre.stream().map(screenMapper::toDTO).collect(Collectors.toList());


        return screenDTOList;

    }

    public void delete(Long screenId) {
        screenRepository.deleteById(screenId);
    }
}