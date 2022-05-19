package com.example.bluegrass.bluegrass.uppgift;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyProcessor implements Processor {

    @Autowired
    WeatherDataRepositry weatherDataRepositry;

    @Autowired
    WeatherService weatherService;

    Logger logger = LoggerFactory.getLogger(MyProcessor.class);

    WeatherData.Reading.Parameter parameter = new WeatherData.Reading.Parameter();

    int x = 0;

    @Transactional
    @Override
    public void process(Exchange exchange) throws Exception {

if (x == 0){

            WeatherData weatherData = new WeatherData();
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

            weatherData.setReading(readings);

            weatherDataRepositry.save(weatherData);
            x++;

}






        List<WeatherData> list = (List<WeatherData>) weatherDataRepositry.findAll();



        String jsonTest= exchange.getIn().getBody(String.class);

        JSONObject rootObj = new JSONObject(jsonTest);
        JSONArray array = (JSONArray) rootObj.get("value");
        JSONObject jsonObject = (JSONObject) array.get(0);


        JSONObject jsonObject1 = (JSONObject) rootObj.get("parameter");
        String nameParameter = jsonObject1.get("name").toString();
        String valueParameter = jsonObject.get("value").toString();


        //System.out.println("Name: "+ nameParameter);
        //System.out.println("Value: "+ valueParameter);


        parameter.setName(nameParameter);
        parameter.setValue(valueParameter);

        exchange.getIn().setBody(parameter);


        WeatherData d = list.get(0);

        if (nameParameter.equals("Lufttemperatur")){
            d.reading.get(0).parameter.get(0).setName(nameParameter);
            d.reading.get(0).parameter.get(0).setValue(valueParameter);
        }else if (nameParameter.equals("Vindriktning")){
            d.reading.get(0).parameter.get(1).setName(nameParameter);
            d.reading.get(0).parameter.get(1).setValue(valueParameter);
        }else if (nameParameter.equals("Vindhastighet")){
            d.reading.get(0).parameter.get(2).setName(nameParameter);
            d.reading.get(0).parameter.get(2).setValue(valueParameter);
        }

        weatherDataRepositry.save(d);







    }
}
