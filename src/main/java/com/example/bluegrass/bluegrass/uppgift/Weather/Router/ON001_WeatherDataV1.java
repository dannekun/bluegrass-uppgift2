package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.*;
import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ON001_WeatherDataV1 extends RouteBuilder {

    JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(WeatherData.class.getPackageName());
    @Autowired
    ON001_ApiNameValueProcessor apiNameValueProcessor;
    @Override
    public void configure() throws Exception {

/*
        from("timer:{{log-inpoint}}?period=5000").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .log("Hämtar lufttemp")
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/3/station/{{station-api}}/period/latest-hour/data.json")
                .setProperty("AirTempResponse", bodyAs(String.class))
                .log("Hämtar Vindriktning")
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/{{station-api}}/period/latest-hour/data.json")
                .setProperty("WindDirectionResponse", bodyAs(String.class))
                .log("Hämtar Vindhastighet")
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/{{station-api}}/period/latest-hour/data.json")
                .setProperty("WindSpeedResponse", bodyAs(String.class))
                .process(apiNameValueProcessor).marshal(jaxbDataFormat)
                .to("log:{{log-endpoint}}-after-mapping?showAll=true")
                .to("activemq:topic:WeatherDataTopicV1")
                .to("file:temp?fileName=${date:now:yyyyMMdd_HHmmss}.xml");



 */




    }
}