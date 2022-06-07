package com.example.bluegrass.bluegrass.uppgift.Weather.Processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class XmlFileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        /*
        File file = new File("./"+ weatherService.getGlobalWeather().getReading().get(0).getStationName()+".xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(weatherService.getGlobalWeather().getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(weatherService.getGlobalWeather(), file);
        marshaller.marshal(weatherService.getGlobalWeather(), stringWriter);
        String xml = stringWriter.toString();
        exchange.getIn().setBody(xml);

         */
        }
}
