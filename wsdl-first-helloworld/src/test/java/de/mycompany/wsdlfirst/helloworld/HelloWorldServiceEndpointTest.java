package de.mycompany.wsdlfirst.helloworld;

import java.net.URL;

import javax.xml.namespace.QName;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.mycompany.wsdlfirst.helloworld.generated.HelloWorldService;
import de.mycompany.wsdlfirst.helloworld.generated.HelloWorldServiceService;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class HelloWorldServiceEndpointTest {

    private static final String WSDL_LOCATION = "http://localhost:8080/test/HelloWorldEndpoint?wsdl";

    @Deployment(testable = false)
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(WebArchive.class, "test.war").addClass(HelloWorldEndpoint.class)
                .addPackage(HelloWorldService.class.getPackage()).addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void callHelloWorldWebService() throws Exception {
        QName qName = new QName("http://wsdlfirst.mycompany.de/", "HelloWorldEndpointService");
        URL url = new URL(WSDL_LOCATION);

        HelloWorldServiceService service = new HelloWorldServiceService(url, qName);
        HelloWorldService helloWorldServicePort = service.getHelloWorldServicePort();
        assertNotNull(helloWorldServicePort);
        assertEquals("Hello Bob", helloWorldServicePort.sayHelloTo("Bob"));
    }

}
