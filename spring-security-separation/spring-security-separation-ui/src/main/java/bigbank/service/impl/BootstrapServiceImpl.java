package bigbank.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import bigbank.service.BankService;
import bigbank.service.BootstrapService;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.service.UserAdministration;

@Service
@Transactional(value = "ui.transactionManager")
public class BootstrapServiceImpl implements BootstrapService{

    private static final Log logger = LogFactory.getLog(BootstrapServiceImpl.class);

    @Autowired
    private UserAdministration userAdministration;
    
    @Autowired
    private BankService bankService;

    @PostConstruct
    public void populateData() {
        Assert.notNull(bankService);

        if (logger.isDebugEnabled()) {
            logger.debug("creating users with roles and rights ...");
        }
        
        Role roleSupervisor = userAdministration.createRoleWithRights("ROLE_SUPERVISOR", "RIGHT_EXTREM_SECURE");

        Role roleUser = userAdministration.createRoleWithRights("ROLE_USER", "RIGHT_STANDARD");

        userAdministration.createUserWithRoles("rod", "koala", roleSupervisor, roleUser);

        userAdministration.createUserWithRoles("user", "user", roleUser);
        if (logger.isDebugEnabled()) {
            logger.debug("creating accounts ...");
        }
        createAccountForUsersWithUsername("rod", "user");
    }

    private void createAccountForUsersWithUsername(String... usernames) {
        for (String username : usernames) {
            bankService.createAccountFor(username, 0.0, 100.0);
        }
    }
   
}
