package de.opitzconsulting.demo.roleright;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.service.UserAdministration;

@ContextConfiguration(locations = { "classpath:/applicationContext-security-test.xml" })
@TransactionConfiguration(transactionManager = "sec.transactionManager")
@ActiveProfiles({ "ide" })
public abstract class AbstractSecurityIntegrationTests extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private UserAdministration userAdministration;

    @Autowired
    private AuthenticationManager authenticationManager;

    protected User createUserWith(String username, String password, String roleStr, String rightName) {
        Role role = this.userAdministration.createRoleWithRights(roleStr, rightName);
        User user = this.userAdministration.createUserWithRoles(username, password, role);
        return user;
    }

    protected void login(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        this.authenticationManager.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    @Autowired
    @Qualifier("sec.dataSource")
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

}
