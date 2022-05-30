package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanGlobal;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class HumanTxtProcessor implements Processor {

    @Autowired
    HumanGlobal humanGlobal;

    @Override
    public void process(Exchange exchange) throws Exception {
        String filename = "files/Human/txt/"+humanGlobal.getHuman().getFirstName()+".txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String print = humanGlobal.getHuman().getFirstName() + " "+ humanGlobal.getHuman().getLastName()+ ", "+ humanGlobal.getHuman().getHairColor()+ ", "+ humanGlobal.getHuman().getBirth();

        writer.println(print);
        writer.close();
        exchange.getIn().setBody(print);
        System.out.println("TXT created");


    }
}
