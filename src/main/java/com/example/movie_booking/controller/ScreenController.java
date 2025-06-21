package com.example.movie_booking.controller;

import com.example.movie_booking.dto.ScreenDTO;
import com.example.movie_booking.model.Screen;
import com.example.movie_booking.service.ScreenService;
import com.example.movie_booking.service.TheatreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/screens")
public class ScreenController {


    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @GetMapping("/{id}")
    List<ScreenDTO> getScreenById(@PathVariable Long id)
    {
        return screenService.getScreensByTheatre(id);



    }
    @PostMapping("/{id}")
    ScreenDTO addScreen(@RequestBody Screen screen, @PathVariable Long id )
    {
        return screenService.save(screen,id);




    }

    @DeleteMapping("/{id}")
    void deleteScreen(@PathVariable Long id)
    {
        screenService.delete(id);

    }



}
