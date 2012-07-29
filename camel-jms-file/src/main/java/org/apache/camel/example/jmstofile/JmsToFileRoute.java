package org.apache.camel.example.jmstofile;

import org.apache.camel.builder.RouteBuilder;

public class JmsToFileRoute extends RouteBuilder {

    @Override
    public void configure() {
        from("test-jms:queue:test.queue").to("file://target/test");
    }
}
