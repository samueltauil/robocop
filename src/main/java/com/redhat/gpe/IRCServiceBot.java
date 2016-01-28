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

    public static final String SBINFOAY = "SBINFOA+";
    public static final String SBINFOAN = "SBINFOA-";
    public static final String MEMFORGE = "MEMFORGE";


    @Override
    public void configure() throws Exception {

            from(createIRCEndpointName())
                    .choice()
                    .when(body().isEqualTo(SBINFOAY))
                        .process(new StudentsWithAccountProcessor()).to("jdbc:mydatasource").to(createIRCEndpointName())

                    .when(body().isEqualTo(SBINFOAN))
                        .process(new StudentsWithoutAccountProcessor()).to("jdbc:mydatasource").to(createIRCEndpointName());

//                    .when(body().isEqualTo(MEMFORGE))    //need to check this, not working yet
//                        .process(new MemoryInfoForge()).to("ssh://stauilrh@forge.opentlc.com?timeout=3000&useFixedDelay=true&delay=5000&certResource=file:/Users/samueltauil/.ssh/id_rsa").to(createIRCEndpointName());


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
