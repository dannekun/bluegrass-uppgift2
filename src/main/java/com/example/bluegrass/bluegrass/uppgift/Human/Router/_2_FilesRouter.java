package com.example.bluegrass.bluegrass.uppgift.Human.Router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class _2_FilesRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:files/Human")
                .log("${body}")
                .to("activemq:fileRouter");
    }
}
