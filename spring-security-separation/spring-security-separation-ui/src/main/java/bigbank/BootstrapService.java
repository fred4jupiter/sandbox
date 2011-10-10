package bigbank;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bigbank.domain.Account;
import bigbank.repository.BankRepository;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.service.UserAdministration;

@Service
public class BootstrapService implements InitializingBean {

    private static final Log logger = LogFactory.getLog(BootstrapService.class);
    
    @Autowired
    private UserAdministration userAdministration;
    
    @Autowired
    private BankRepository bankRepository;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(bankRepository);
        Assert.notNull(userAdministration);
        
        if (logger.isDebugEnabled()) {
            logger.debug("creating users with roles and rights ...");
        }
        Role roleSupervisor = userAdministration.createRoleWithRights("ROLE_SUPERVISOR", "RIGHT_EXTREM_SECURE");
        
        Role roleUser = userAdministration.createRoleWithRights("ROLE_USER", "RIGHT_STANDARD");
        
        userAdministration.createUserWithRoles("rod", "koala", roleSupervisor, roleUser);
        
        userAdministration.createUserWithRoles("user", "user", roleUser);       
        
        createAccountForUsersWithUsername("rod", "user");
    }
    
    private void createAccountForUsersWithUsername(String... usernames) {
        for (String username : usernames) {
            bankRepository.createOrUpdateAccount(new Account(username));
        }
    } 

}
