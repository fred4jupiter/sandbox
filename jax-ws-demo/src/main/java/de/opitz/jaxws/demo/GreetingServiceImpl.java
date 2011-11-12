package de.opitz.jaxws.demo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import org.springframework.stereotype.Service;

// start jetty and your WS will be available at: http://localhost:8080/ws/greeting?wsdl
@Service("greetingService")
@WebService(serviceName = "GreetingService")
@SOAPBinding(style = Style.RPC, use = Use.LITERAL)
public class GreetingServiceImpl implements GreetingService {

    @WebMethod
    @Override
    public String greet(String name) {
        return "Hello " + name;
    }

}
