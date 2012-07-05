package com.opitzconsulting.spring.cxf.client;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/applicationContext-client.xml" })
public abstract class AbstractClientIntegrationTest extends AbstractJUnit4SpringContextTests {

}
