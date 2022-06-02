package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

@Component
public class OFF02_ClassProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("EXCHANGE " + exchange.getIn().getBody());
        Human human = exchange.getIn().getBody(Human.class);

        exchange.getIn().setHeader("CamelFileName", "ID: "+human.getHjid() + " " +
                human.getFirstName() + " " + human.getLastName());

        exchange.getIn().setBody(human);
    }
}
