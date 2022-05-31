package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanGlobal;
import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

@Component
public class GlobalXMLFileProcessor implements Processor {


    @Autowired
    HumanGlobal humanGlobal;

    @Override
    public void process(Exchange exchange) throws Exception {
        File file = new File("files/Human/XML/"+humanGlobal.getHuman().getFirstName() +".xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Human.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(humanGlobal.getHuman(), file);
        marshaller.marshal(humanGlobal.getHuman(), stringWriter);
        String xml = stringWriter.toString();
        exchange.getIn().setBody(xml);
        System.out.println("XML created");
    }
}
