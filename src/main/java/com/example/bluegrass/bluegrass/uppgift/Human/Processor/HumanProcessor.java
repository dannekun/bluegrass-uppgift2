package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanGlobal;
import com.fasterxml.jackson.databind.ObjectMapper;
import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class HumanProcessor implements Processor {

    Human human = new Human();

    @Override
    public void process(Exchange exchange) throws Exception {
        human.setFirstName("Daniel");
        human.setLastName("Bojic");
        human.setHairColor("Brown");
        DateTimeFormatter originalDateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dateString = "26/05/1994";
        LocalDate date = LocalDate.parse(dateString, originalDateFormatter);
        XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance()
                .newXMLGregorianCalendar(date.toString());

        human.setBirth(xmlDate);
        human.setBirthItem(xmlDate.toGregorianCalendar().getTime());

        File file = new File("files/Human/"+human.getFirstName()+".json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.writeValue(file,human);
        String json = objectMapper.writeValueAsString(human);
        exchange.getIn().setBody(json);



    }
}
