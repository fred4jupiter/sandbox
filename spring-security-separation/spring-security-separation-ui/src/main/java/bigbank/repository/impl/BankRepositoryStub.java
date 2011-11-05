package bigbank.repository.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;

//@Repository("bankRepository")
public class BankRepositoryStub implements BankRepository {
    private long id = 0;
    private final Map<Long, Account> accounts = new HashMap<Long, Account>();

    public void createOrUpdateAccount(Account account) {
        if (account.getId() == -1) {
            id++;
            account.setId(id);
        }
        accounts.put(new Long(account.getId()), account);
        System.out.println("SAVE: " + account);
    }

    public Collection<Account> findAccounts() {
        Collection<Account> accounts = this.accounts.values();
        System.out.println("Returning " + accounts.size() + " account(s):");
        for (Account account : accounts) {
            System.out.println(" > " + account);
        }
        return accounts;
    }

    public Account readAccount(Long id) {
        return accounts.get(id);
    }

}
