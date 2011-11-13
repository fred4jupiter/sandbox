package bigbank.repository.impl;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository("bankRepository")
public class BankRepositoryImpl implements BankRepository {

    private static final Log logger = LogFactory.getLog(BankRepositoryImpl.class);

    @PersistenceContext(unitName = "ui.entityManagerFactory")
    private EntityManager entityManager;

    public void createOrUpdateAccount(Account account) {
        if (account.getId() == null) {
            entityManager.persist(account);
        }
        else {
            entityManager.merge(account);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("SAVE: " + account);
        }
    }

    public Collection<Account> findAccounts() {
        TypedQuery<Account> query = entityManager.createQuery("Select a from Account a", Account.class);
        List<Account> accounts = query.getResultList();
        if (logger.isDebugEnabled()) {
            logger.debug("findAccounts: Returning " + accounts.size()+ " account(s):");
        }
        return accounts;
    }

    public Account readAccount(Long id) {
        return entityManager.find(Account.class, id);
    }

}
