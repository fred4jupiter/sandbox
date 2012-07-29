package org.apache.camel.example.jmstofile;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelDemo {

    public void execute() {
        try {
            CamelContext context = new DefaultCamelContext();
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
            context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
            context.addRoutes(new JmsToFileRoute());
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            for (int i = 0; i < 10; i++) {
                template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
            }
            
            // wait for processing messages in queue
            Thread.sleep(1000);
            context.stop();
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
