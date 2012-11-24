package com.opitzconsulting.spring.cxf.client;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.ws.addressing.AddressingBuilder;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.junit.Test;

import com.opitzconsulting.spring.generated.HelloWorldEndpoint;
import com.opitzconsulting.spring.generated.HelloWorldEndpointService;

public class HelloWorldClientAsyncTest {

    @Test
    public void callSayHelloAsync() {
        HelloWorldEndpointService implementor = new HelloWorldEndpointService();

        AddressingBuilder builder = AddressingBuilder.getAddressingBuilder();
        AddressingProperties maps = builder.newAddressingProperties();
        AttributedURIType replyTo = new AttributedURIType();
        replyTo.setValue("http://localhost:8282/services/HelloWorldCallback");
        EndpointReferenceType replyToRef = new EndpointReferenceType();
        replyToRef.setAddress(replyTo);
        maps.setReplyTo(replyToRef);

        HelloWorldEndpoint helloWorldEndpointPort = implementor.getHelloWorldEndpointPort();

        Map<String, Object> requestContext = ((BindingProvider) helloWorldEndpointPort).getRequestContext();

        requestContext.put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, maps);

        helloWorldEndpointPort.sayHi("Michael");
    }
}
