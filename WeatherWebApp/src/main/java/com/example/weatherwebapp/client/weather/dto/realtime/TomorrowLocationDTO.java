package com.example.weatherwebapp.client.weather.dto.realtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TomorrowLocationDTO(
        @JsonProperty("lat") Double latitude,
        @JsonProperty("lon") Double longitude,
        String name,
        String type
)
{ }
