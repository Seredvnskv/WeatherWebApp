package com.example.weatherwebapp.model;

import lombok.Builder;

@Builder
public record WeatherDTO(
        double temperature,
        double humidity,
        double windSpeed,
        double visibility
) { }
