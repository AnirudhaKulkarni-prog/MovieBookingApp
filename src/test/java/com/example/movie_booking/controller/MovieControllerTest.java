package com.example.movie_booking.controller;

import com.example.movie_booking.model.Movie;
import com.example.movie_booking.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(com.example.movie_booking.controller.MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMovies_returnsListOfMovies() throws Exception {
        List<Movie> mockMovies = List.of(
                new Movie(1L, "Interstellar", "Sci-Fi", "English", 169, 8.6),
                new Movie(2L, "Inception", "Sci-Fi", "English", 148, 8.8)
        );



        when(movieService.getAllMovies()).thenReturn(mockMovies);

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].title").value("Interstellar"))
                .andExpect(jsonPath("$[1].rating").value(8.8));
    }


    @Test
    void testMovieByTitle_returnsAMovie() throws Exception {

        Movie mockMovie=  new Movie(2L, "Inception", "Sci-Fi", "English", 148, 8.8);

        when(movieService.getMovieByTitle("Inception")).thenReturn(mockMovie);

        mockMvc.perform(get("/movies"+"/"+ "Inception")).andExpect(status().isOk()).andExpect(
                jsonPath("$.title").value("Inception")).andExpect(jsonPath("$.genre").value("Sci-Fi"));
    }

    @Test
    void testAddMovie_returnsCreatedMovie() throws Exception {
        Movie inputMovie=  new Movie(null, "Rege", "Thriller", "English", 148, 8.8);

        Movie savedMovie=  new Movie(10L, "Rege", "Thriller", "English", 148, 8.8);
        when(movieService.addMovie(any(Movie.class))).thenReturn(savedMovie);

        mockMvc.perform(post("/movies").contentType("application/json").content(objectMapper.writeValueAsString(inputMovie))).andExpect(
                status().isCreated())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.title").value("Rege"));

    }

    @Test
    void testDeleteMovie_returnsEmptyResponse() throws Exception {
        Long idToDelete = 5L;

        // no need to stub void methods unless you want to verify
        doNothing().when(movieService).deleteMovie(idToDelete);

        mockMvc.perform(delete("/movies/{id}", idToDelete))
                .andExpect(status().isNoContent());

    }

}
