package com.example.weatherwebapp.controller;

import com.example.weatherwebapp.model.WeatherDTO;
import com.example.weatherwebapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("")
    public ResponseEntity<WeatherDTO> getRealTimeWeather(@RequestParam(required = true) String location) {
        return ResponseEntity.ok(weatherService.getRealTimeWeather(location.toLowerCase()));
    }

    @GetMapping("/coordinates")
    public ResponseEntity<WeatherDTO> getWeatherByCoords(
            @RequestParam double lat,
            @RequestParam double lon) {
        return ResponseEntity.ok(weatherService.getRealTimeWeather(lat, lon));
    }
}
