package com.example.weatherwebapp.service;

import com.example.weatherwebapp.client.weather.WeatherClient;
import com.example.weatherwebapp.client.weather.dto.forecast.TomorrowForecastDTO;
import com.example.weatherwebapp.client.weather.dto.realtime.TomorrowRealTimeWeatherDTO;
import com.example.weatherwebapp.mapper.WeatherMapper;
import com.example.weatherwebapp.model.WeatherDTO;
import com.example.weatherwebapp.model.WeatherQuery;
import com.example.weatherwebapp.repository.WeatherQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherQueryRepository weatherQueryRepository;

    @Autowired
    public WeatherService(WeatherClient weatherClient, WeatherQueryRepository weatherQueryRepository) {
        this.weatherClient = weatherClient;
        this.weatherQueryRepository = weatherQueryRepository;
    }

    @Cacheable(value = "weatherCache", key = "#latitude + ',' + #longitude")
    public WeatherDTO getRealTimeWeather(double latitude, double longitude) {
        return processWeatherResponse(weatherClient.getRealTimeWeather(latitude, longitude));
    }

    @Cacheable(value = "weatherCache", key = "#location")
    public WeatherDTO getRealTimeWeather(String location) {
        return processWeatherResponse(weatherClient.getRealTimeWeather(location));
    }

    @Cacheable(value = "forecastCache", key = "#location+'-forecast'")
    public TomorrowForecastDTO getWeatherForecast(String location) {
        return weatherClient.getWeatherForecast(location);
    }

    private WeatherDTO processWeatherResponse(TomorrowRealTimeWeatherDTO dto) {
        this.weatherQueryRepository.save(WeatherMapper.mapToWeatherQuery(dto));
        return WeatherMapper.mapToWeatherDTO(dto);
    }

    public List<WeatherQuery> getAllQueries() {
        return weatherQueryRepository.findAll();
    }
 }
