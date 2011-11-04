package de.opitzconsulting.demo.roleright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.service.UserAdministration;

@ContextConfiguration(locations = {"classpath:/META-INF/spring/applicationContext-security-test.xml"})
public abstract class AbstractIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private UserAdministration userAdministration;
    
    protected User createUserWith(String username, String password, String roleStr, String rightName) {
        Role role = this.userAdministration.createRoleWithRights(roleStr, rightName);
        User user = this.userAdministration.createUserWithRoles(username, password, role);
        return user;
    }

}
