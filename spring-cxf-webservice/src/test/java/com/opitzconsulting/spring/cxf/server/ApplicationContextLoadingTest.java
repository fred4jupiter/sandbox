package com.opitzconsulting.spring.cxf.server;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations = { "classpath:/applicationContext-server.xml" })
public class ApplicationContextLoadingTest extends AbstractJUnit4SpringContextTests{

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ApplicationContextLoadingTest.class);
    
    @Test
    public void loadContext() {
        LOGGER.debug("creating application context...");
        // nothing
    }
}
