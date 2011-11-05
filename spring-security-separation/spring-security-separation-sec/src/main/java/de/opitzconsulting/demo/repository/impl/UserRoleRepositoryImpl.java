package de.opitzconsulting.demo.repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.domain.UserRole;
import de.opitzconsulting.demo.repository.UserRoleRepository;

@Repository("userRoleRepository")
public class UserRoleRepositoryImpl extends AbstractBaseRepository implements UserRoleRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findByUser(User user) {
        Query query = getEntityManager().createQuery("Select u.role from UserRole u where u.user = :user");
        query.setParameter("user", user);
        return (List<Role>) query.getResultList();
    }

    @Override
    public UserRole save(UserRole userRole) {
        if (userRole.getId() == null) {
            getEntityManager().persist(userRole);
            return userRole;
        } else {
            return getEntityManager().merge(userRole);
        }
    }
}
