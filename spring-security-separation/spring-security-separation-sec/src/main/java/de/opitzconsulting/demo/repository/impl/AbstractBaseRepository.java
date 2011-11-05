package de.opitzconsulting.demo.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractBaseRepository {

    @PersistenceContext(unitName = "spring-demo")
    private EntityManager entityManager;
    
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
