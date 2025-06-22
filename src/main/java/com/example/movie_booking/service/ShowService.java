package com.example.movie_booking.service;

import com.example.movie_booking.dto.CreateShowRequestDTO;
import com.example.movie_booking.dto.ShowDTO;
import com.example.movie_booking.exception.OverlappingShowsException;
import com.example.movie_booking.mappers.ShowMapper;
import com.example.movie_booking.model.Movie;
import com.example.movie_booking.model.Screen;
import com.example.movie_booking.model.Show;
import com.example.movie_booking.repository.MovieRepository;
import com.example.movie_booking.repository.ScreenRepository;
import com.example.movie_booking.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    private final MovieRepository movieRepository;

    private final ScreenRepository screenRepository;

    private final ShowMapper showMapper;


    public ShowService(ShowRepository showRepository, MovieRepository movieRepository, ScreenRepository screenRepository, ShowMapper showMapper) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
        this.showMapper = showMapper;
    }

    ShowDTO addShow(CreateShowRequestDTO requestDTO)
    {
        Movie relatedMovie=movieRepository.findById(requestDTO.getMovieId()).orElseThrow( ()-> new NoSuchElementException("No movie found for this show"));
        Screen relatedScreen= screenRepository.findById(requestDTO.getScreenId()).orElseThrow(()->new NoSuchElementException("No screen present for the show"));

      /**  if(!showRepository.findOverLappingShows(requestDTO.getScreenId(), requestDTO.getStartTime(),requestDTO.getEndTime()).isEmpty())
        {
            throw new OverlappingShowsException("Shows already running on this screen at the provided time");
        }**/

       validateScreenAvailability(requestDTO);

        Show show = showMapper.toEntity(requestDTO, relatedMovie, relatedScreen);
        return showMapper.toDTO(showRepository.save(show));

    }


    private void validateScreenAvailability(CreateShowRequestDTO requestDTO) {
        boolean conflictExists = !showRepository.findOverLappingShows(
                requestDTO.getScreenId(),
                requestDTO.getStartTime(),
                requestDTO.getEndTime()
        ).isEmpty();

        if (conflictExists) {
            throw new OverlappingShowsException("Screen is occupied during this time slot");
        }
    }







}
