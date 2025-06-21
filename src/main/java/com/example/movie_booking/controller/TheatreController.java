package com.example.movie_booking.controller;

import com.example.movie_booking.model.Theatre;
import com.example.movie_booking.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheatreController {
    private final TheatreService theatreService;

    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public ResponseEntity<List<Theatre>> getAll() {
        return ResponseEntity.ok(theatreService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getById(@PathVariable Long id) {
        return ResponseEntity.ok(theatreService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Theatre> save(@RequestBody Theatre theatre) {
        return new ResponseEntity<>(theatreService.save(theatre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        theatreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}