package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class OFF002_HumanJsonV1Process implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Human human = exchange.getIn().getBody(Human.class);
/*
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.writeValue(new File("files/Human/json/"+ human.getFirstName()+ ".json"), human);
        System.out.println("JSON created");

 */

    }
}
