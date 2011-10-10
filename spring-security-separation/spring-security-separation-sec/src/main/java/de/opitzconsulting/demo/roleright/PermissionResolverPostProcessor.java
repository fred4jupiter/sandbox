package de.opitzconsulting.demo.roleright;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;

/**
 * This is a <code>BeanPostProcessor</code> for changing the
 * <code>AuthenticationProvider</code> against an
 * <code>AuthenticationProviderPermissionDecorator</code>. This decorator
 * delegates authentication requests against the original
 * <code>AuthenticationProvider</code> with an <code>IPermissionResolver</code>
 * to resolves given roles to their underlying permissions.
 * 
 * @author staehler_m1
 * 
 */
@Component
public class PermissionResolverPostProcessor implements BeanPostProcessor {

    private static final Log logger = LogFactory.getLog(PermissionResolverPostProcessor.class);
    
    /**
     * Prefix that will be used for rights in CAN
     */
    public static final String RIGHT_PREFIX = "RIGHT_";

    @Autowired
    private PermissionResolver permissionResolver;

    public Object postProcessAfterInitialization(Object bean, String beanName) {

        if (bean instanceof DaoAuthenticationProvider) {
            logger.debug("changes DaoAuthenticationProvider to AuthenticationProviderPermissionDecorator");
            // here we use the decorator for decorating the
            // AuthenticationProvider with an PermissionResolver
            return new AuthenticationProviderPermissionDecorator((AuthenticationProvider) bean, permissionResolver);
        }

        // register a right voter
        if (bean instanceof AbstractAccessDecisionManager) {
            AbstractAccessDecisionManager abstractAccessDecisionManager = (AbstractAccessDecisionManager) bean;
            abstractAccessDecisionManager.getDecisionVoters().add(createRightVoter());
        }

        return bean;
    }

    private RoleVoter createRightVoter() {
        RoleVoter rightVoter = new RoleVoter();
        rightVoter.setRolePrefix(RIGHT_PREFIX);
        return rightVoter;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

}
