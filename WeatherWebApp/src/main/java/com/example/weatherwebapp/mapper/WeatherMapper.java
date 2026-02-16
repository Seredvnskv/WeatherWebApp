package com.example.weatherwebapp.mapper;

import com.example.weatherwebapp.client.weather.dto.TomorrowRealTimeWeatherDTO;
import com.example.weatherwebapp.model.WeatherDTO;

public class WeatherMapper {
    public static WeatherDTO toWeatherDTO(TomorrowRealTimeWeatherDTO dto) {
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
}
