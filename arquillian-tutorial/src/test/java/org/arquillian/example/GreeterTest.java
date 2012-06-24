package org.arquillian.example;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class GreeterTest {

    @Inject    
    private Greeter greeter;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addClasses(Greeter.class, PhraseBuilder.class, GreetingTextProvider.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void sayHelloTo() {
        assertEquals("Hello Fred", greeter.sayHelloTo("Fred"));
    }

    @Test
    public void sayHelloWithPhraseBuilderTo() {
        assertEquals("Hello, Fred!", greeter.sayHelloWithPhraseBuilderTo("Fred"));
    }
}