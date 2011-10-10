package de.opitzconsulting.demo.service;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService{
    
    @Override
    public String sayHelloTo(String name) {
        return "Hello "+name;
    }

    @Override
    public String sayHelloWithRoleCheckTo(String name) {
        return "Hello "+name;
    }

}
