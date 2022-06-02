package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import com.example.bluegrass.bluegrass.uppgift.Weather.WeatherService;
import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.GregorianCalendar;

@Component
public class ON001_ApiNameValueProcessor implements Processor {

    @Transactional
    @Override
    public void process(Exchange exchange) {

        WeatherData weatherData = new WeatherData();

        String airTempResponse = exchange.getProperty("AirTempResponse", String.class);
        String windDirectionResponse = exchange.getProperty("WindDirectionResponse", String.class);
        String windSpeedResponse = exchange.getProperty("WindSpeedResponse", String.class);

        WeatherData.Reading.Parameter airTempParam = createParam(airTempResponse);
        WeatherData.Reading.Parameter windDirectionParam = createParam(windDirectionResponse);
        WeatherData.Reading.Parameter windSpeedParam = createParam(windSpeedResponse);

        // Create new reading with configured values
        try {
            WeatherData.Reading reading = createReading(windSpeedResponse);
            reading.getParameter().add(airTempParam);
            reading.getParameter().add(windDirectionParam);
            reading.getParameter().add(windSpeedParam);

            weatherData.getReading().add(reading);
        }
        catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error mapping api-responses to WeatherData", e);
        }

        exchange.getIn().setBody(weatherData);
    }

    public WeatherData.Reading.Parameter createParam(String apiResponse) {
        JSONObject apiJson = new JSONObject(apiResponse);
        JSONArray apiValueJsonArray = (JSONArray) apiJson.get("value");
        JSONObject apiValueJson = (JSONObject) apiValueJsonArray.get(0);
        JSONObject apiParameter = (JSONObject) apiJson.get("parameter");
        String nameParameter = apiParameter.get("name").toString();
        String valueParameter = apiValueJson.get("value").toString();

        WeatherData.Reading.Parameter param = new WeatherData.Reading.Parameter();
        param.setName(nameParameter);
        param.setValue(valueParameter);

        return param;
    }

    public WeatherData.Reading createReading(String apiResponse) throws DatatypeConfigurationException {

        WeatherData.Reading reading = new WeatherData.Reading();

        JSONObject rootObj = new JSONObject(apiResponse);
        JSONObject jsonObject1 = (JSONObject) rootObj.get("station");
        String nameParameter = jsonObject1.get("name").toString();
        String stationKey = jsonObject1.get("key").toString();
        //parameter.setName(nameParameter);
        //exchange.getIn().setBody(parameter);

        reading.setStationName(nameParameter);
        BigInteger stationKeyInt = new BigInteger(stationKey);
        reading.setStationId(stationKeyInt);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        reading.setTimestamp(now);

        return reading;
    }
}
