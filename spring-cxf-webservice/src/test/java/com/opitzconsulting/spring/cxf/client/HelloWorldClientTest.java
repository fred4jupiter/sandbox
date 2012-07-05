package com.opitzconsulting.spring.cxf.client;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.opitzconsulting.spring.cxf.HelloWorldEndpoint;

import static org.junit.Assert.*;


public class HelloWorldClientTest extends AbstractClientIntegrationTest{

    /**
     * The wsdl url is: http://localhost:8282/services/HelloWorld?wsdl
     */
    @Autowired
    private HelloWorldEndpoint helloWorldService;
    
    @Test
    public void callSayHelloToOnWebServiceMethod() {        
        assertEquals("Hello Michael", helloWorldService.sayHi("Michael"));
    }
}
