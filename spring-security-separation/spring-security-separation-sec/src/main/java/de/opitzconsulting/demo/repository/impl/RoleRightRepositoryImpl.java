package de.opitzconsulting.demo.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.Right;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.RoleRight;
import de.opitzconsulting.demo.repository.RoleRightRepository;

@Repository("roleRightRepository")
public class RoleRightRepositoryImpl extends AbstractBaseRepository implements RoleRightRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Right> findByRole(Role role) {
        Query query = getEntityManager().createQuery("Select rr.right from RoleRight rr where rr.role = :role");
        query.setParameter("role", role);
        return (List<Right>) query.getResultList();
    }

    @Override
    public RoleRight save(RoleRight roleRight) {
        if (roleRight.getId() == null) {
            getEntityManager().persist(roleRight);
            return roleRight;
        } else {
            return getEntityManager().merge(roleRight);
        }
    }

}
