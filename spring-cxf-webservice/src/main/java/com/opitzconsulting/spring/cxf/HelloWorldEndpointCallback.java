package com.opitzconsulting.spring.cxf;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;

import org.springframework.stereotype.Service;

@WebService
@Service("helloWorldEndpointCallback")
public class HelloWorldEndpointCallback {

    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://cxf.spring.opitzconsulting.com/")
    @Oneway
    public void sayHello(String value) {
        System.out.println("This is the callback result: " + value);
    }
}
