package de.opitzconsulting.demo.repository.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import de.opitzconsulting.demo.domain.Right;
import de.opitzconsulting.demo.repository.RightRepository;

@Repository("rightRepository")
public class RightRepositoryImpl extends AbstractBaseRepository implements RightRepository {

    @Override
    public Right findByName(String name) {
        Query query = getEntityManager().createQuery("Select r from Right r where r.name = :name");
        query.setParameter("name", name);
        return (Right) query.getSingleResult();
    }

    @Override
    public Right save(Right right) {
        if (right.getId() == null) {
            getEntityManager().persist(right);
            return right;
        } else {
            return getEntityManager().merge(right);
        }
    }

}
