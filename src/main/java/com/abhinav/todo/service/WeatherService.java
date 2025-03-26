package com.abhinav.todo.service;

import com.abhinav.todo.cache.AppCache;
import com.abhinav.todo.entity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${WEATHER_API_KEY}")
    private  String API_KEY;

    @Autowired
    AppCache appCache;
//    private  String API_REQUEST="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponse getWeather(String city) {
        String finalApi=appCache.cache.get(AppCache.appKeys.API_REQUEST.toString()).replace("<CITY>", city).replace("<API_KEY>", API_KEY);
        WeatherResponse weatherResponse = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class).getBody();
        return weatherResponse;
    }
}

