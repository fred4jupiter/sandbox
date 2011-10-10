package de.opitzconsulting.demo.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.repository.RoleRepository;

@Repository("roleRepository")
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findByName(String name) {
        Query query = entityManager.createQuery("Select r from Role r where r.name = :name");
        query.setParameter("name", name);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            this.entityManager.persist(role);
            return role;
        }
        else {
            return this.entityManager.merge(role);
        }        
    }
}
