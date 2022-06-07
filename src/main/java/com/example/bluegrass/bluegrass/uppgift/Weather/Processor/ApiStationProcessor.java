package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;


@Component
public class ApiStationProcessor implements Processor {
    WeatherData.Reading.Parameter parameter = new WeatherData.Reading.Parameter();

    @Override
    public void process(Exchange exchange) throws Exception {
        /*
        String jsonTest= exchange.getIn().getBody(String.class);
        JSONObject rootObj = new JSONObject(jsonTest);
        JSONObject jsonObject1 = (JSONObject) rootObj.get("station");
        String nameParameter = jsonObject1.get("name").toString();
        String stationKey = jsonObject1.get("key").toString();
        parameter.setName(nameParameter);
        exchange.getIn().setBody(parameter);
        weatherService.getGlobalWeather().getReading().get(0).setStationName(nameParameter);
        BigInteger stationKeyInt = new BigInteger(stationKey);
        weatherService.getGlobalWeather().getReading().get(0).setStationId(stationKeyInt);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        weatherService.getGlobalWeather().getReading().get(0).setTimestamp(now);

         */

    }
}
