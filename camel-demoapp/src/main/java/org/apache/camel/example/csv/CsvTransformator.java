package org.apache.camel.example.csv;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class CsvTransformator implements Processor {

    @SuppressWarnings("unchecked")
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        Map<String, Object> modelMap = (Map<String, Object>) in.getBody();
        ExampleCsv exampleCsv = (ExampleCsv) modelMap.get(ExampleCsv.class.getCanonicalName());
        System.out.println(exampleCsv);
        final String newContent = "This is the new content " + System.currentTimeMillis();

        exampleCsv.setContent(newContent);
        in.setBody(exampleCsv);
    }

}
