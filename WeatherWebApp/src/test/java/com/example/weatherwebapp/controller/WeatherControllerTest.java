package com.example.weatherwebapp.controller;

import com.example.weatherwebapp.model.WeatherDTO;
import com.example.weatherwebapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)

class WeatherControllerTest {
    @MockitoBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    private WeatherDTO weatherDTO;

    @BeforeEach
    void setUp() {
        weatherDTO = WeatherDTO.builder()
                .temperature(15.0)
                .humidity(60.0)
                .visibility(10.0)
                .pressureSeaLevel(1013.0)
                .rainIntensity(0.0)
                .snowIntensity(0.0)
                .freezingRainIntensity(0.0)
                .sleetIntensity(0.0)
                .cloudCover(25.0)
                .windDirection(180.0)
                .windSpeed(5.5)
                .build();
    }

    @Test
    void shouldCreateMockMvc() {
        assertNotNull(mockMvc);
    }

    @Nested
    class weatherByCoordinatesTest {
        @Test
        void shouldReturnWeatherByCoords() throws Exception {
            double lat = 52.23;
            double lon = 21.01;

            when(weatherService.getRealTimeWeather(lat, lon)).thenReturn(weatherDTO);

            mockMvc.perform(get("/api/v1/weather")
                            .param("lat", String.valueOf(lat))
                            .param("lon", String.valueOf(lon)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.temperature").value(15.0))
                    .andExpect(jsonPath("$.humidity").value(60.0))
                    .andExpect(jsonPath("$.visibility").value(10.0))
                    .andExpect(jsonPath("$.windSpeed").value(5.5))
                    .andExpect(jsonPath("$.pressureSeaLevel").value(1013.0))
                    .andExpect(jsonPath("$.rainIntensity").value(0.0))
                    .andExpect(jsonPath("$.snowIntensity").value(0.0))
                    .andExpect(jsonPath("$.freezingRainIntensity").value(0.0))
                    .andExpect(jsonPath("$.sleetIntensity").value(0.0))
                    .andExpect(jsonPath("$.cloudCover").value(25.0))
                    .andExpect(jsonPath("$.windDirection").value(180.0));
        }

        @Test
        void shouldReturnBadRequestWhenLatIsMissing() throws Exception {
            mockMvc.perform(get("/api/v1/weather")
                            .param("lon", "21.01"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLonIsMissing() throws Exception {
            mockMvc.perform(get("/api/v1/weather")
                            .param("lat", "52.23"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLatIsInvalid() throws Exception {
            mockMvc.perform(get("/api/v1/weather")
                            .param("lat", "invalid")
                            .param("lon", "21.01"))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void shouldReturnBadRequestWhenLonIsInvalid() throws Exception {
            mockMvc.perform(get("/api/v1/weather")
                            .param("lat", "52.23")
                            .param("lon", "invalid"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class weatherByLocationTests {
        @Test
        void shouldReturnWeatherByLocation() throws Exception {
            String location = "Gdańsk";

            when(weatherService.getRealTimeWeather(location.toLowerCase())).thenReturn(weatherDTO);

            mockMvc.perform(get("/api/v1/weather/location")
                            .param("location", location))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.temperature").value(15.0))
                    .andExpect(jsonPath("$.humidity").value(60.0))
                    .andExpect(jsonPath("$.visibility").value(10.0))
                    .andExpect(jsonPath("$.windSpeed").value(5.5));
        }

        @Test
        void shouldReturnBadRequestWhenLocationIsMissing() throws Exception {
            mockMvc.perform(get("/api/v1/weather/location"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class weatherQueryHistoryTest {
        @Test
        void shouldReturnWeatherQueryHistory() throws Exception {
            mockMvc.perform(get("/api/v1/weather/history"))
                    .andExpect(status().isOk());
        }
    }

    @Nested
    class weatherForecastTests {
        @Test
        void shouldReturnWeatherForecast() throws Exception {
            mockMvc.perform(get("/api/v1/weather/forecast")
                            .param("location", "Gdańsk"))
                    .andExpect(status().isOk());
        }

        @Test
        void shouldReturnBadRequestWhenLocationIsMissing() throws Exception {
            mockMvc.perform(get("/api/v1/weather/forecast"))
                    .andExpect(status().isBadRequest());
        }
    }
}