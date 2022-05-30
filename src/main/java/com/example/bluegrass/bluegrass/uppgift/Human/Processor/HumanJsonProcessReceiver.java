package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanGlobal;
import com.example.bluegrass.bluegrass.uppgift.classes.Human;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;

@Component
public class HumanJsonProcessReceiver implements Processor {

    @Autowired
    HumanGlobal humanGlobal;
    @Override
    public void process(Exchange exchange) throws Exception {

        humanGlobal.setHuman((Human) exchange.getIn().getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.writeValue(new File("files/Human/json/"+ humanGlobal.getHuman().getFirstName()+ ".json"), humanGlobal.getHuman());
        System.out.println("JSON created");

    }
}
