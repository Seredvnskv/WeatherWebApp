package com.example.weatherwebapp.mapper;

import com.example.weatherwebapp.client.weather.dto.realtime.TomorrowRealTimeWeatherDTO;
import com.example.weatherwebapp.model.WeatherDTO;
import com.example.weatherwebapp.model.WeatherQuery;

public class WeatherMapper {
    public static WeatherDTO mapToWeatherDTO(TomorrowRealTimeWeatherDTO dto) {
        return WeatherDTO.builder()
                .temperature(dto.data().values().temperature())
                .humidity(dto.data().values().humidity())
                .visibility(dto.data().values().visibility())
                .pressureSeaLevel(dto.data().values().pressureSeaLevel())
                .rainIntensity(dto.data().values().rainIntensity())
                .snowIntensity(dto.data().values().snowIntensity())
                .freezingRainIntensity(dto.data().values().freezingRainIntensity())
                .sleetIntensity(dto.data().values().sleetIntensity())
                .cloudCover(dto.data().values().cloudCover())
                .windDirection(dto.data().values().windDirection())
                .windSpeed(dto.data().values().windSpeed())
                .build();
    }

    public static WeatherQuery mapToWeatherQuery(TomorrowRealTimeWeatherDTO dto) {
        return WeatherQuery.builder()
                .time(dto.data().time())
                .latitude(dto.location().latitude())
                .longitude(dto.location().longitude())
                .location(dto.location().name() != null ? dto.location().name() : "Unknown Location")
                .temperature(dto.data().values().temperature())
                .humidity(dto.data().values().humidity())
                .windSpeed(dto.data().values().windSpeed())
                .build();
    }
}
