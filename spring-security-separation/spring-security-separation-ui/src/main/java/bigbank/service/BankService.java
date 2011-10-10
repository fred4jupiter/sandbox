package bigbank.service;

import org.springframework.security.access.prepost.PreAuthorize;

import bigbank.domain.Account;

public interface BankService {

    public Account readAccount(Long id);

    public Account[] findAccounts();

    @PreAuthorize("hasRole('RIGHT_EXTREM_SECURE') and (#account.balance + #amount >= -#account.overdraft)")
    public Account post(Account account, double amount);
}
