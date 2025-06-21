package com.example.movie_booking.service;

import com.example.movie_booking.dto.TheatreDTO;
import com.example.movie_booking.mappers.TheatreMapper;
import com.example.movie_booking.model.Theatre;
import com.example.movie_booking.repository.TheatreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    private final TheatreMapper theatreMapper;

    public TheatreService(TheatreRepository theatreRepository, TheatreMapper theatreMapper) {
        this.theatreRepository = theatreRepository;
        this.theatreMapper = theatreMapper;
    }

    public List<TheatreDTO> getAll() {
        List<Theatre> theatreList=theatreRepository.findAll();
        List<TheatreDTO> theatreDTOList=theatreList.stream().map(theatreMapper::toDto).collect(Collectors.toList());
        return theatreDTOList;
    }

    public TheatreDTO getById(Long id) {


        Theatre theatre=theatreRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Theatre not found"));
        return theatreMapper.toDto(theatre);

    }

    public TheatreDTO save(Theatre theatre) {

        Theatre savedTheatre=theatreRepository.save(theatre);
        return theatreMapper.toDto(savedTheatre);
    }

    public void delete(Long id) {
        theatreRepository.deleteById(id);
    }
}
