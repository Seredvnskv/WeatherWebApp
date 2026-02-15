package com.example.weatherwebapp.client.geocode.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ReverseGeocodeDTO(
        @JsonProperty("display_name") String name,
        AddressDTO address
) {}