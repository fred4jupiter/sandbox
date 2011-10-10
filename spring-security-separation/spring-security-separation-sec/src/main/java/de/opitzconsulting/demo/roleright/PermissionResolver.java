package de.opitzconsulting.demo.roleright;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * Resolves roles to permissions
 * 
 * @author staehler_m1
 * 
 */
public interface PermissionResolver {

    String ROLE_PREFIX = "ROLE_";

    String PERMISSION_PREFIX = "RIGHT_";

    /**
     * Resolve permissions. Returns all permissions that belongs to given roles
     * 
     * @param roleAuthorities
     *            the role authorities
     * @return the granted authority[]
     */
    Collection<GrantedAuthority> resolvePermissions(Collection<? extends GrantedAuthority> roleAuthorities);

}
