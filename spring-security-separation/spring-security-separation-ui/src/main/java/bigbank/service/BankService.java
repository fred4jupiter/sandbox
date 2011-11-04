package bigbank.service;

import org.springframework.security.access.prepost.PreAuthorize;

import bigbank.domain.Account;

import java.util.Collection;
import java.util.List;

public interface BankService {

    Account readAccount(Long id);

    Collection<Account> findAccounts();

    @PreAuthorize("hasRole('RIGHT_EXTREM_SECURE') and (#account.balance + #amount >= -#account.overdraft)")
    Account post(Account account, double amount);

    Account createAccountFor(String holder, double balance, double overdraft);
}
