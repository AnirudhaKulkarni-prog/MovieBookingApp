package com.example.movie_booking.controller;

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
    List<Screen> getScreenById(@PathVariable Long Id)
    {
        return screenService.getScreensByTheatre(Id);



    }
    @PostMapping("/{id}")
    Screen addScreen(@RequestBody Screen screen, @PathVariable Long id )
    {
        return screenService.save(screen,id);

    }


}
