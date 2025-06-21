package com.example.movie_booking.service;

import com.example.movie_booking.model.Screen;
import com.example.movie_booking.model.Theatre;
import com.example.movie_booking.repository.ScreenRepository;
import com.example.movie_booking.repository.TheatreRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ScreenService {
    private final ScreenRepository screenRepository;
    private final TheatreRepository theatreRepository;

    public ScreenService(ScreenRepository screenRepository, TheatreRepository theatreRepository) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<Screen> getAll() {
        return screenRepository.findAll();
    }

    public Screen save(Screen screen, Long theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(() -> new NoSuchElementException("Theatre not found"));
        screen.setTheatre(theatre);
        return screenRepository.save(screen);
    }

    public List<Screen> getScreensByTheatre(Long theatreId) {
        return screenRepository.findByTheatreId(theatreId);
    }

    public void delete(Long screenId) {
        screenRepository.deleteById(screenId);
    }
}