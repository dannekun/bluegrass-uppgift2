package com.example.bluegrass.bluegrass.uppgift.Weather;

import com.example.bluegrass.bluegrass.uppgift.classes.WeatherData;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2022-05-19
 * Time: 16:58
 * Project: bluegrass-uppgift
 * Copyright: MIT
 */

@Service
public class WeatherService {
    WeatherData globalWeather = new WeatherData();

    @PostConstruct
    public void startList(){
        WeatherData.Reading reading = new WeatherData.Reading();

        WeatherData.Reading.Parameter parameter1 = new WeatherData.Reading.Parameter();
        WeatherData.Reading.Parameter parameter2 = new WeatherData.Reading.Parameter();
        WeatherData.Reading.Parameter parameter3 = new WeatherData.Reading.Parameter();
        List<WeatherData.Reading.Parameter> parameters = new ArrayList<>();

        parameters.add(parameter1);
        parameters.add(parameter2);
        parameters.add(parameter3);
        reading.setParameter(parameters);

        List<WeatherData.Reading> readings = new ArrayList<>();
        readings.add(reading);

        globalWeather.setReading(readings);
    }


    public WeatherData getGlobalWeather() {
        return globalWeather;
    }

    public void setGlobalWeather(WeatherData globalWeather) {
        this.globalWeather = globalWeather;
    }

    public String stringWeather(WeatherData weatherData){
        return "StationId: "+ weatherData.getReading().get(0).getStationId() + ", StationName: "+ weatherData.getReading().get(0).getStationName() +
                ", Timestamp: "+ weatherData.getReading().get(0).getTimestamp() + ", "+ weatherData.getReading().get(0).getParameter().get(0).getName() +
                ": " + weatherData.getReading().get(0).getParameter().get(0).getValue() + ", "+ weatherData.getReading().get(0).getParameter().get(1).getName() +
                ": " + weatherData.getReading().get(0).getParameter().get(1).getValue() + ", "+ weatherData.getReading().get(0).getParameter().get(2).getName() +
                ": " + weatherData.getReading().get(0).getParameter().get(2).getValue();
    }

}
