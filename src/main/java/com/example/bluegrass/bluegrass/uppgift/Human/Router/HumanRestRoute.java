package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import com.example.bluegrass.bluegrass.uppgift.Human.HumanBean;
import com.example.bluegrass.bluegrass.uppgift.Human.HumanRepository;
import com.example.bluegrass.bluegrass.uppgift.Human.Processor.HumanJsonProcessReceiver;
import com.example.bluegrass.bluegrass.uppgift.classes.Human;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class HumanRestRoute extends RouteBuilder {

    @Autowired
    HumanBean humanBean;

    @Autowired
    HumanRepository humanRepository;

    @Autowired
    HumanJsonProcessReceiver humanJsonProcessReceiver;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").host("localhost").port(8080).bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");

        //rest().path("/addHuman").post().consumes(MediaType.APPLICATION_JSON_VALUE).type(Human.class).outType(Human.class).to("log:test");
        //rest().post("/human").type(Human.class).consumes("application/json").bindingMode(RestBindingMode.json).produces("application/json").to("log:test");

        //from("timer:rest-api-consumer?period=10000").log("${body}").to("rest:get:/human").log("${body}");

        //restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
        // Define REST Endpoints


        //rest().path("/human").consumes("application/json").produces("application/json").get().to("direct:hello").post().type(Human.class).outType(Human.class).to("bean:humanBean");

        //from("direct:hello").transform().constant("Hello World").log("${body}");

        //restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

        rest().consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
                .get("/human").outType(Human.class).to("direct:getHuman")
                .post("/human").type(Human.class).to("direct:saveHuman");

        from("direct:saveHuman").process(this::saveHuman).log("${body}");

        from("direct:getHuman").process(this::getHuman).log("${body}");
    }


    private void saveHuman(Exchange exchange){
        Human human = (Human) exchange.getIn().getBody();
        humanRepository.save(human);
        human = humanRepository.findTopByOrderByHjidDesc();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        try {
            objectMapper.writeValue(new File("files/Human/"+ human.getFirstName()+ ".json"), human);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void getHuman(Exchange exchange){
        List<Human> allHuman = humanRepository.findAll();
        Message message = new DefaultMessage(exchange.getContext());
        message.setBody(allHuman);
        exchange.setMessage(message);
    }
}
