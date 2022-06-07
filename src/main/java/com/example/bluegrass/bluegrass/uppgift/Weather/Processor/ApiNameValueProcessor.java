package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ApiNameValueProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        /*
        String apiJsonString= exchange.getIn().getBody(String.class);
        JSONObject apiJson = new JSONObject(apiJsonString);
        JSONArray apiValueJsonArray = (JSONArray) apiJson.get("value");
        JSONObject apiValueJson = (JSONObject) apiValueJsonArray.get(0);
        JSONObject apiParameter = (JSONObject) apiJson.get("parameter");
        String nameParameter = apiParameter.get("name").toString();
        String valueParameter = apiValueJson.get("value").toString();
        if (nameParameter.equals("Lufttemperatur")){
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(0).setName(nameParameter);
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(0).setValue(valueParameter);
            positionInArray = 0;
        }else if (nameParameter.equals("Vindriktning")){
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(1).setName(nameParameter);
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(1).setValue(valueParameter);
            positionInArray = 1;
        }else if (nameParameter.equals("Vindhastighet")){
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(2).setName(nameParameter);
            weatherService.getGlobalWeather().getReading().get(0).getParameter().get(2).setValue(valueParameter);
            positionInArray = 2;
        }
        exchange.getIn().setBody(weatherService.getGlobalWeather().getReading().get(0).getParameter().get(positionInArray));
        */
    }


}
