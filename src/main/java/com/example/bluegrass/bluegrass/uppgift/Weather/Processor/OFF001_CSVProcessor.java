package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class OFF001_CSVProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        WeatherData weatherData = exchange.getIn().getBody(WeatherData.class);

        Map<String, String> map = new LinkedHashMap<>();

        map.put("Timestamp",weatherData.getReading().get(0).getTimestamp().toString());
        map.put("StationName",weatherData.getReading().get(0).getStationName());
        map.put("Temp",weatherData.getReading().get(0).getParameter().get(0).getValue());
        map.put("WindDirection",weatherData.getReading().get(0).getParameter().get(1).getValue());
        map.put("WindSpeed",weatherData.getReading().get(0).getParameter().get(2).getValue());

        exchange.getIn().setBody(map);
        }

}
