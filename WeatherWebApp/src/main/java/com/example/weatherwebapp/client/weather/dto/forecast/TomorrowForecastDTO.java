package com.example.weatherwebapp.client.weather.dto.forecast;

import com.example.weatherwebapp.client.weather.dto.realtime.TomorrowLocationDTO;

public record TomorrowForecastDTO(
        TomorrowTimelinesDTO timelines,
        TomorrowLocationDTO location
)
{ }
