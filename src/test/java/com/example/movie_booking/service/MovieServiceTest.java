package com.example.movie_booking.service;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.text.html.Option;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetAllMovies_returnsListOfMovies() {
        List<Movie> mockMovies = List.of(
                new Movie(1L, "Interstellar", "Sci-Fi", "English", 169, 8.6),
                new Movie(2L, "Inception", "Sci-Fi", "English", 148, 8.8)
        );

        when(movieRepository.findAll()).thenReturn(mockMovies);

        //Check the result

        List<Movie> result = movieService.getAllMovies();

        assertEquals(2, result.size());
        assertEquals("Interstellar", result.get(0).getTitle());

        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void testGetMovieByTitle_ReturnsMovie()
    {
        Movie movie=  new Movie(2L, "Inception", "Sci-Fi", "English", 148, 8.8);

        when(movieRepository.findByTitle("Inception")).thenReturn(Optional.of(movie));

        Movie result=movieService.getMovieByTitle("Inception");


        assertNotNull(result);

        assertEquals("Inception",result.getTitle());

    }

    @Test
    void testGetMovieByTitle_MovieNotFound_ThrowsException() {
        // Given
        when(movieRepository.findByTitle("Avatar")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class , () -> movieService.getMovieByTitle("Avatar"));


    }


}
