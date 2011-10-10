package de.opitzconsulting.demo.service;

import org.springframework.security.access.prepost.PreAuthorize;

public interface DemoService {
    
    @PreAuthorize("hasRole('RIGHT_USER')")
    String sayHelloTo(String name);
    
    @PreAuthorize("hasRole('ROLE_USER')")
    String sayHelloWithRoleCheckTo(String name);
}
