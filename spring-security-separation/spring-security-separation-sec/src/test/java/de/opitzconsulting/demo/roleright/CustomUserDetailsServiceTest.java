package de.opitzconsulting.demo.roleright;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import de.opitzconsulting.demo.service.CustomUserDetailsService;

import static org.junit.Assert.*;

public class CustomUserDetailsServiceTest extends AbstractIntegrationTests {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void checkInjection() {
        assertNotNull(customUserDetailsService);
        createUserWith("michael", "michael", "ROLE_USER", "RIGHT_STANDARD");

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("michael");
        assertNotNull(userDetails);
        assertEquals("michael", userDetails.getUsername());
    }
}
