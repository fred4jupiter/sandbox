package de.mycompany.wsdlfirst.helloworld;

import javax.jws.WebMethod;
import javax.jws.WebService;

import de.mycompany.wsdlfirst.helloworld.generated.HelloWorldService;

@WebService(name = "HelloWorldService", targetNamespace = "http://wsdlfirst.mycompany.de/")
public class HelloWorldServiceEndpoint implements HelloWorldService{

    @WebMethod
    public String sayHelloTo(String name) {
        return "Hello " + name;
    }
}
