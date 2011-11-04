package de.opitzconsulting.demo.roleright;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.service.DemoService;

public class RoleRightTest extends AbstractSecurityIntegrationTests {

    @Autowired
    private DemoService demoService;

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

}
