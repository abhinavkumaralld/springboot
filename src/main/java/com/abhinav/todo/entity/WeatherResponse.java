package com.abhinav.todo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResponse{
    public Request request;
    public Location location;
    public Current current;
    public static class  AirQuality{
        public String co;
        public String no2;
        public String o3;
        public String so2;
        public String pm2_5;
        public String pm10;
    }

    public static class Astro{
        public String sunrise;
        public String sunset;
        public String moonrise;
        public String moonset;
        public String moon_phase;
        public int moon_illumination;
    }

    public static class Current{
        @JsonProperty("observation_time")
        public String observationTime;
        public int temperature;
        public int weather_code;
        public ArrayList<String> weather_icons;
        public ArrayList<String> weather_descriptions;
        public Astro astro;
        public AirQuality air_quality;
        public int wind_speed;
        public int wind_degree;
        public String wind_dir;
        public int pressure;
        public int precip;
        public int humidity;
        public int cloudcover;
        public int feelslike;
        public int uv_index;
        public int visibility;
        public String is_day;
    }

    public static class Location{
        public String name;
        public String country;
        public String region;
        public String lat;
        public String lon;
        public String timezone_id;
        public String localtime;
        public int localtime_epoch;
        public String utc_offset;
    }

    public static class Request{
        public String type;
        public String query;
        public String language;
        public String unit;
    }

}






