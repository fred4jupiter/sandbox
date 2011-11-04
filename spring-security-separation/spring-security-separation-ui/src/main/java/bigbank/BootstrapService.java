package bigbank;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.access.ContextSingletonBeanFactoryLocator;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import bigbank.service.BankService;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.service.UserAdministration;

@Service
public class BootstrapService {

    private static final Log logger = LogFactory.getLog(BootstrapService.class);

    @Autowired
    private BankService bankService;

    @PostConstruct
    public void populateData() {
        Assert.notNull(bankService);

        if (logger.isDebugEnabled()) {
            logger.debug("creating users with roles and rights ...");
        }
        UserAdministration userAdministration = getUserAdministration();

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

    private UserAdministration getUserAdministration() {
        BeanFactoryLocator locator = ContextSingletonBeanFactoryLocator.getInstance();
        BeanFactoryReference bfr = locator.useBeanFactory("separation-sec.context");
        BeanFactory factory = bfr.getFactory();
        UserAdministration userAdministration = factory.getBean(UserAdministration.class);
        bfr.release();
        return userAdministration;
    }

}
