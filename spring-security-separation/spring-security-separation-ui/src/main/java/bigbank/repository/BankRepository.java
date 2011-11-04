package bigbank.repository;

import bigbank.domain.Account;

import java.util.Collection;
import java.util.List;

public interface BankRepository {
    Account readAccount(Long id);

    void createOrUpdateAccount(Account account);

    Collection<Account> findAccounts();
}
