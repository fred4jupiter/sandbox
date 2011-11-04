package bigbank;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;
import static junit.framework.Assert.*;

@ContextConfiguration(locations = { "classpath:applicationContext-business.xml", "classpath:beanRefContext.xml" })
public class AccountTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BankRepository bankRepository;

    @Test
    public void createAccoutAndCheckIfExistend() {

        // two accounts will be created in BootstrapService
        Collection<Account> accounts = bankRepository.findAccounts();
        assertEquals(2, accounts.size());
    }

}
