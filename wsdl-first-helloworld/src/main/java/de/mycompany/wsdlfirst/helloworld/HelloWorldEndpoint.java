package de.mycompany.wsdlfirst.helloworld;

import javax.jws.WebMethod;
import javax.jws.WebService;

import de.mycompany.wsdlfirst.helloworld.generated.HelloWorldService;

/**
 * see real online wsdl under: http://localhost:8080/wsdl-first-helloworld/HelloWorldEndpoint?wsdl
 * 
 */
@WebService(name = "HelloWorldService", targetNamespace = "http://wsdlfirst.mycompany.de/")
public class HelloWorldEndpoint implements HelloWorldService{

    @WebMethod
    public String sayHelloTo(String name) {
        return "Hello " + name;
    }
}
