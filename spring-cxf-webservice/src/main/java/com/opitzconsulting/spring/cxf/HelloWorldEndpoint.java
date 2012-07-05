package com.opitzconsulting.spring.cxf;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opitzconsulting.spring.cxf.service.GreetingService;

@WebService
@Service("helloWorldEndpoint")
public class HelloWorldEndpoint {

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(HelloWorldEndpoint.class);

    @Autowired
    private GreetingService greetingService;

    public String sayHi(String text) {
        LOGGER.debug("calling greeting service with text=" + text);
        return greetingService.sayGreetingTo(text);
    }
}
