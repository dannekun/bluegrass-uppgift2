package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import generated.WeatherData;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class OFF001_TextFileProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        WeatherData weatherData = exchange.getIn().getBody(WeatherData.class);
        String filename = "./"+weatherData.getReading().get(0).getStationName()+".txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String stringToPrint = weatherData.getReading().get(0).getTimestamp()+
                "," + weatherData.getReading().get(0).getStationName()+
                ","+ weatherData.getReading().get(0).getParameter().get(0).getValue()+
                "," + weatherData.getReading().get(0).getParameter().get(1).getValue() +
                ","+ weatherData.getReading().get(0).getParameter().get(2).getValue();

        writer.println(stringToPrint);
        writer.close();
        exchange.getIn().setBody(stringToPrint);
        }

}
