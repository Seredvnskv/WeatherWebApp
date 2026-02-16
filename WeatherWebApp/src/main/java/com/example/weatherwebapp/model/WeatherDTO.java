package com.example.weatherwebapp.model;

import lombok.Builder;

@Builder
public record WeatherDTO(
        Double temperature,
        Double humidity,
        Double visibility,
        Double pressureSeaLevel,
        Double rainIntensity,
        Double snowIntensity,
        Double freezingRainIntensity,
        Double sleetIntensity,
        Double cloudCover,
        Double windDirection,
        Double windSpeed
) { }
