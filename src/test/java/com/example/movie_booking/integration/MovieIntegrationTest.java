package com.example.movie_booking.integration;



import com.example.movie_booking.model.Movie;
import com.example.movie_booking.repository.MovieRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MovieRepository movieRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/movies";
    }

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
    }

    @Test
    @Order(1)
    void testAddMovieAndFetchAll() {
        Movie newMovie = new Movie(null, "Oppenheimer", "Biography", "English", 180, 8.5);

        ResponseEntity<Movie> postResponse = restTemplate.postForEntity(getBaseUrl(), newMovie, Movie.class);
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody());
        assertEquals("Oppenheimer", postResponse.getBody().getTitle());

        ResponseEntity<Movie[]> getResponse = restTemplate.getForEntity(getBaseUrl(), Movie[].class);
        assertEquals(1, Objects.requireNonNull(getResponse.getBody()).length);
        assertEquals("Oppenheimer", getResponse.getBody()[0].getTitle());
    }

    @Test
    @Order(2)
    void testDeleteMovieById() {
        Movie movie = new Movie(null, "Dunkirk", "War", "English", 120, 7.9);
        Movie saved = movieRepository.save(movie);

        restTemplate.delete(getBaseUrl() + "/" + saved.getId());

        Optional<Movie> result = movieRepository.findById(saved.getId());
        assertTrue(result.isEmpty());
    }

    @Test
    @Order(3)
    void testGetMovieByTitle() {
        Movie movie = new Movie(null, "Inception", "Sci-Fi", "English", 148, 8.8);
        movieRepository.save(movie);

        ResponseEntity<Movie> response = restTemplate.getForEntity(getBaseUrl() + "/Inception", Movie.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Inception", response.getBody().getTitle());
    }

    @Test
    @Order(4)
    void testGetMovieByTitle_NotFound() {

        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl() + "/UnknownTitle", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
