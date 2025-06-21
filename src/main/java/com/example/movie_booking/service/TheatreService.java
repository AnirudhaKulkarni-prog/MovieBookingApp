package com.example.movie_booking.service;

import com.example.movie_booking.model.Theatre;
import com.example.movie_booking.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getAll() {
        return theatreRepository.findAll();
    }

    public Theatre getById(Long id) {
        return theatreRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Theatre not found"));
    }

    public Theatre save(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public void delete(Long id) {
        theatreRepository.deleteById(id);
    }
}
