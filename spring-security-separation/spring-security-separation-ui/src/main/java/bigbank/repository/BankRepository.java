package bigbank.repository;

import bigbank.domain.Account;

public interface BankRepository {
    Account readAccount(Long id);

    void createOrUpdateAccount(Account account);

    Account[] findAccounts();
}
