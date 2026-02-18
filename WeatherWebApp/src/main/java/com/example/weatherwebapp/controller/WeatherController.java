package com.example.weatherwebapp.controller;

import com.example.weatherwebapp.client.weather.dto.forecast.TomorrowForecastDTO;
import com.example.weatherwebapp.model.WeatherDTO;
import com.example.weatherwebapp.model.WeatherQuery;
import com.example.weatherwebapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("")
    public ResponseEntity<WeatherDTO> getWeatherByCoords(
            @RequestParam double lat,
            @RequestParam double lon)
    {
        return ResponseEntity.ok(weatherService.getRealTimeWeather(lat, lon));
    }

    @GetMapping("/location")
    public ResponseEntity<WeatherDTO> getRealTimeWeather(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getRealTimeWeather(location.toLowerCase()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<WeatherQuery>> getWeatherQueryHistory() {
        return ResponseEntity.ok(weatherService.getAllQueries());
    }

    @GetMapping("/forecast")
    public ResponseEntity<TomorrowForecastDTO> getWeatherForecast(@RequestParam String location) {
        return ResponseEntity.ok(weatherService.getWeatherForecast(location.toLowerCase()));
    }
}
