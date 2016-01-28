package com.redhat.gpe;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by samueltauil on 12/21/15.
 */
public class MemoryInfoForge implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setBody("free -m%0A");
    }
}
