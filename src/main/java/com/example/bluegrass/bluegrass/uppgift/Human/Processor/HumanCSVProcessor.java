package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanGlobal;
import com.github.opendevl.JFlat;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class HumanCSVProcessor implements Processor {


    @Autowired
    HumanGlobal humanGlobal;



    @Override
    public void process(Exchange exchange) throws Exception {

        String str = new String(Files.readAllBytes(Paths.get("files/Human/json/"+humanGlobal.getHuman().getFirstName()+".json")));

        JFlat jFlat = new JFlat(str);

        jFlat.json2Sheet().write2csv("files/Human/csv/"+humanGlobal.getHuman().getFirstName()+".csv");
        System.out.println("CSV created");
    }
}
