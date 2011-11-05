package de.opitzconsulting.demo.repository.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl extends AbstractBaseRepository implements UserRepository {

    @Override
    public User findByUsername(String username) {
        Query query = getEntityManager().createQuery("Select u from User u where u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            getEntityManager().persist(user);
            return user;
        } else {
            return getEntityManager().merge(user);
        }
    }
}
