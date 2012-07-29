package org.apache.camel.example.csv;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;
import org.junit.Test;

public class CsvTransformationTest {

    @Test
    public void readFromCsvFileTransformAndSaveToNewFile() throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(createRoute());
        context.start();

        // wait for processing messages in queue
        Thread.sleep(1000);
        context.stop();
    }

    public RouteBuilder createRoute() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                DataFormat bindy = new BindyCsvDataFormat("org.apache.camel.example.csv");
                //@formatter:off
                from("file:data/inbox?noop=true")
                .unmarshal(bindy)
                .split(body())
                .process(new CsvTransformator())
                .marshal(bindy)
                .to("file:data/outbox").end();
                //@formatter:on
            }
        };
    }

    public RouteBuilder createSimpleCopyRoute() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("file:data/inbox?noop=true").to("file:data/outbox");
            }
        };
    }
}
