package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.github.opendevl.JFlat;
import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class HumanCSVProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        Human human = exchange.getIn().getBody(Human.class);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("ID", human.getHjid().toString());
        map.put("FirstName",human.getFirstName());
        map.put("LastName",human.getLastName());
        map.put("HairColor",human.getHairColor());
        map.put("Birthday", human.getBirth().toString());

        exchange.getIn().setBody(map);
    }
}
