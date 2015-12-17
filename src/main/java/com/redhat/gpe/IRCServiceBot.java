package com.redhat.gpe;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by samueltauil on 12/11/15.
 */
@Component
public class IRCServiceBot extends RouteBuilder {

//    @Override
//    public void configure() throws Exception {
//            from(createIRCEndpointName()).process(new Processor() {
//                public void process(Exchange exchange) throws Exception {
//                    System.out.println(exchange.getIn().getBody().toString());
//                }
//            });
//    }

   //using lambda in processor
    @Override
    public void configure() throws Exception {

        from(createIRCEndpointName())
                .process((exchange) -> {
                    System.out.println(exchange.getIn().getBody().toString());
                });
    }

    public Properties loadProperties() throws IOException {

        Properties ircprops = new Properties();

        InputStream inputStream = IRCServiceBot.class.getClassLoader().getResourceAsStream("irc.properties");
        ircprops.load(inputStream);

        return ircprops;
    }

    public String createIRCEndpointName() throws IOException {
        Properties props = loadProperties();
        String endpoint = "irc:" + props.getProperty("nickname") + "@"
                + props.getProperty("server") + ":"
                + props.getProperty("port") + "/#"
                + props.getProperty("channel") + "?nickname="
                + props.getProperty("nickname");
        System.out.println(endpoint);
        return endpoint;
    }
}
