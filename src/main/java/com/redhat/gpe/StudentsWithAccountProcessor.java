package com.redhat.gpe;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by samueltauil on 12/18/15.
 */
public class StudentsWithAccountProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn().setBody("SELECT count(*) as 'Number of Students with Skills Base Account'  FROM skills_tracker.employee_assessment where processed_flag='Y' and processed_date_time >= '2014-12-07 00:00:00';");

    }
}
