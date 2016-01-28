package com.redhat.gpe;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by samueltauil on 12/18/15.
 */
public class StudentsWithoutAccountProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setBody("SELECT count(*) as 'Number of Students who were not sent to Skills Base' FROM skills_tracker.employee_assessment where processed_flag='N' and insert_date_time >= '2015-12-07 00:00:00';");

    }
}
