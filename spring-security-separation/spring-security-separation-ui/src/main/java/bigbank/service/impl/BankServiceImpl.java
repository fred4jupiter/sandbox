package bigbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;
import bigbank.service.BankService;

@Service("bankService")
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        Assert.notNull(bankRepository);
        this.bankRepository = bankRepository;
    }

    public Account[] findAccounts() {
        return this.bankRepository.findAccounts();
    }

    public Account post(Account account, double amount) {
        Assert.notNull(account);

        // We read account bank from DAO so it reflects the latest balance
        Account a = bankRepository.readAccount(account.getId());
        if (a == null) {
            throw new IllegalArgumentException("Couldn't find requested account");
        }

        a.setBalance(a.getBalance() + amount);
        bankRepository.createOrUpdateAccount(a);
        return a;
    }

    public Account readAccount(Long id) {
        return bankRepository.readAccount(id);
    }
}
