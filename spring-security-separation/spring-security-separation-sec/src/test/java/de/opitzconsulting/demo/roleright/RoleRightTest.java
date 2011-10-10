package de.opitzconsulting.demo.roleright;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.service.DemoService;

import static org.junit.Assert.*;

public class RoleRightTest extends AbstractIntegrationTests {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DemoService demoService;

    @Before
    public void doBefore() {

    }

    @After
    public void doAfter() {
        logout();
    }

    private void logout() {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void loginAndCallDemoService() {
        User user = createUserWith("michael", "michael", "ROLE_USER", "RIGHT_USER");
        login(user.getUsername(), user.getPassword());

        String greeting = this.demoService.sayHelloTo("Michael");
        assertEquals("Hello Michael", greeting);
    }

    @Test
    public void loginAndCallDemoServiceWithRoleAssigned() {
        User user = createUserWith("michael", "michael", "ROLE_USER", "RIGHT_USER");
        login(user.getUsername(), user.getPassword());

        String greeting = this.demoService.sayHelloWithRoleCheckTo("Michael");
        assertEquals("Hello Michael", greeting);
    }

    @Test
    public void callDemoServiceWithoutLogin() {
        try {
            this.demoService.sayHelloTo("Michael");
        } catch (AuthenticationCredentialsNotFoundException e) {
            // expected
        }
    }

    private void login(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        this.authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
