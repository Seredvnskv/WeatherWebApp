package com.example.weatherwebapp.client.weather.dto.realtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TomorrowDataDTO(
        LocalDateTime time,
        TomorrowValuesDTO values
)
{ }
