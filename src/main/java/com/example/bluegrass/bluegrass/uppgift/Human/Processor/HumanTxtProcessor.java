package com.example.bluegrass.bluegrass.uppgift.Human.Processor;

import generated.Human;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Component
public class HumanTxtProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        Human human = exchange.getIn().getBody(Human.class);

        String filename = "files/Human/txt/"+ human.getFirstName()+".txt";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(filename, true));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String print = human.getFirstName() + " "+ human.getLastName()+ ", "+ human.getHairColor()+ ", "+ human.getBirth();

        writer.println(print);
        writer.close();
        exchange.getIn().setBody(print);
        System.out.println("TXT created");


    }
}
