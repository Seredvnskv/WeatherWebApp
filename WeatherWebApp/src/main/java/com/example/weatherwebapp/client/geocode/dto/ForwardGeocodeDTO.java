package com.example.weatherwebapp.client.geocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForwardGeocodeDTO(
        @JsonProperty("lat") String latitude, @JsonProperty("lon") String longitude, @JsonProperty("display_name") String name)
{ }
