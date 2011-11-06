package bigbank;

import static junit.framework.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;

public class AccountTest extends AbstractUiIntegrationTests {

    @Autowired
    private BankRepository bankRepository;

    @Test
    public void createAccoutAndCheckIfExistend() {

        // two accounts will be created in BootstrapService
        Collection<Account> accounts = bankRepository.findAccounts();
        assertEquals(2, accounts.size());
    }

    @Test
    public void createApplicationContext() {
        // checks creating the application context
    }

}
