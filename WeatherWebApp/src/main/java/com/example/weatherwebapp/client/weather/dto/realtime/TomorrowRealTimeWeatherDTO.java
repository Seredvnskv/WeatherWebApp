package com.example.weatherwebapp.client.weather.dto.realtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TomorrowRealTimeWeatherDTO(
        TomorrowDataDTO data,
        TomorrowLocationDTO location
)
{ }
