package com.example.bluegrass.bluegrass.uppgift.Weather.Router;

import com.example.bluegrass.bluegrass.uppgift.Weather.Processor.*;
import com.example.bluegrass.bluegrass.uppgift.classes.WeatherData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeatherRouter extends RouteBuilder {
    JacksonDataFormat jsonDataFormat = new JacksonDataFormat(WeatherData.Reading.Parameter.class);
    @Autowired
    ApiNameValueProcessor apiNameValueProcessor;
    @Autowired
    ApiStationProcessor apiStationProcessor;
    @Autowired
    XmlFileProcessor xmlFileProcessor;
    @Autowired
    TextFileProcessor textFileProcessor;
    @Autowired
    DatabaseProcessor databaseProcessor;

    int x = 0;
    @Override
    public void configure() throws Exception {
        /*
        from("timer:{{log-inpoint}}?period={{call-timer}}").setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/3/station/{{station-api}}/period/latest-hour/data.json")
                .process(apiNameValueProcessor).marshal(jsonDataFormat)
                .to("log:{{log-endpoint}}")
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/1/station/{{station-api}}/period/latest-hour/data.json")
                .process(apiNameValueProcessor).marshal(jsonDataFormat)
                .to("log:{{log-endpoint}}")
                .to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/{{station-api}}/period/latest-hour/data.json")
                .process(apiNameValueProcessor).marshal(jsonDataFormat)
                .to("log:{{log-endpoint}}").to("https://opendata-download-metobs.smhi.se/api/version/1.0/parameter/4/station/{{station-api}}/period/latest-hour/data.json")
                .process(apiStationProcessor).marshal(jsonDataFormat)
                .to("log:{{log-endpoint}}")
                .process(xmlFileProcessor)
                .to("log:{{log-endpoint}}")
                .process(textFileProcessor)
                .to("log:{{log-endpoint}}")
                .process(databaseProcessor)
                .to("log:{{log-endpoint}}");

         */


    }
}