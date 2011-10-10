package de.opitzconsulting.demo.repository;

import de.opitzconsulting.demo.domain.Right;

public interface RightRepository{

    Right findByName(String name);
    
    Right save(Right right);
}
