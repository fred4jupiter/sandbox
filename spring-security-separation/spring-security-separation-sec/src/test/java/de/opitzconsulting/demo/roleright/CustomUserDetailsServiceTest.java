package de.opitzconsulting.demo.roleright;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class CustomUserDetailsServiceTest extends AbstractSecurityIntegrationTests {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Test
    public void checkInjection() {
        assertNotNull(customUserDetailsService);
        createUserWith("michael", "michael", "ROLE_USER", "RIGHT_STANDARD");

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("michael");
        assertNotNull(userDetails);
        assertEquals("michael", userDetails.getUsername());
    }
}
