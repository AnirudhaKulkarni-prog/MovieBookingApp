package com.example.movie_booking.dto;

import java.util.List;

public class TheatreDTO {
    private Long id;
    private String name;

    public TheatreDTO() {
    }

    public TheatreDTO(Long id, String name, String city, List<ScreenDTO> screens) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.screens = screens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<ScreenDTO> getScreens() {
        return screens;
    }

    public void setScreens(List<ScreenDTO> screens) {
        this.screens = screens;
    }

    private String city;
    private List<ScreenDTO> screens;
}