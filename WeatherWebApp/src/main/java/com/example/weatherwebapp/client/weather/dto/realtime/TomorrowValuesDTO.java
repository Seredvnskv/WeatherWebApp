package com.example.weatherwebapp.client.weather.dto.realtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TomorrowValuesDTO(
    Double cloudCover,
    Double temperature,
    Double humidity,
    Double pressureSeaLevel,
    Double visibility,
    Double rainIntensity,
    Double snowIntensity,
    Double freezingRainIntensity,
    Double sleetIntensity,
    Double windDirection,
    Double windSpeed,
    Double weatherCode
)
{ }
