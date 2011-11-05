package bigbank.repository;

import java.util.Collection;

import bigbank.domain.Account;

public interface BankRepository {
    Account readAccount(Long id);

    void createOrUpdateAccount(Account account);

    Collection<Account> findAccounts();
}
