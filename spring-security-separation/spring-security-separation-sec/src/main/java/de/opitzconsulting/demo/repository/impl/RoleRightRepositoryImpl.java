package de.opitzconsulting.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.Right;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.RoleRight;
import de.opitzconsulting.demo.repository.RoleRightRepository;

@Repository("roleRightRepository")
public class RoleRightRepositoryImpl implements RoleRightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Right> findByRole(Role role) {
        Query query = entityManager.createQuery("Select rr.right from RoleRight rr where rr.role = :role");
        query.setParameter("role", role);
        return (List<Right>) query.getResultList();
    }

    @Override
    public RoleRight save(RoleRight roleRight) {
        if (roleRight.getId() == null) {
            this.entityManager.persist(roleRight);
            return roleRight;
        } else {
            return this.entityManager.merge(roleRight);
        }
    }

}
