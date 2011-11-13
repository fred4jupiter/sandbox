package de.opitzconsulting.demo.roleright;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.opitzconsulting.demo.domain.Right;

/**
 * Mapps role to permissions
 * 
 * @author staehler_m1
 * 
 */
@Component("permissionResolver")
public class DefaultPermissionResolver implements PermissionResolver {

    @PersistenceContext(unitName = "sec.entityManagerFactory")
    private EntityManager entityManager;

    @Transactional(value = "sec.transactionManager", readOnly = true)
    public Collection<GrantedAuthority> resolvePermissions(Collection<? extends GrantedAuthority> roleAuthorities) {

        // this hashMap contains all permissions
        HashSet<GrantedAuthority> allPermissions = new HashSet<GrantedAuthority>();

        for (GrantedAuthority roleAuth : roleAuthorities) {

            if (roleAuth.getAuthority().startsWith(PERMISSION_PREFIX)) {
                allPermissions.add(roleAuth);
            } else {
                // resolve role to permission
                Set<GrantedAuthority> permissionsOfRole = resolveRoleToPermissions(roleAuth);
                allPermissions.addAll(permissionsOfRole);
            }
        }

        return allPermissions;
    }

    /**
     * Resolves a role to the underlying permissions
     * 
     * @param roleAuth
     *            given role
     * @return set of permissions based on given role
     */
    private Set<GrantedAuthority> resolveRoleToPermissions(GrantedAuthority roleAuth) {

        final String roleName = roleAuth.getAuthority();

        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();

        if (roleName.startsWith(ROLE_PREFIX)) {

            List<Right> rightList = findRightsByRoleName(roleName);
            for (Right right : rightList) {
                grantedAuthorities.add(new GrantedAuthorityImpl(right.getName()));
            }
        }

        return grantedAuthorities;
    }

    @SuppressWarnings("unchecked")
    private List<Right> findRightsByRoleName(String roleName) {
        Query query = this.entityManager.createQuery("Select rr.right from RoleRight rr where rr.role.name = :roleName");
        query.setParameter("roleName", roleName);
        return query.getResultList();
    }

}
