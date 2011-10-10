package de.opitzconsulting.demo.roleright;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationProviderPermissionDecorator implements AuthenticationProvider {

    private AuthenticationProvider authenticationProvider;

    private PermissionResolver permissionResolver;

    public AuthenticationProviderPermissionDecorator(AuthenticationProvider authenticationProvider, PermissionResolver permissionResolver) {
        this.authenticationProvider = authenticationProvider;
        this.permissionResolver = permissionResolver;
    }

    public Authentication authenticate(Authentication authentication) {

        Authentication response = this.authenticationProvider.authenticate(authentication);
        Collection<? extends GrantedAuthority> roles = response.getAuthorities();

        Collection<GrantedAuthority> permissions = permissionResolver.resolvePermissions(roles);

        Collection<GrantedAuthority> allGrantedAuthorities = new LinkedList<GrantedAuthority>();
        addAllIfNotContains(allGrantedAuthorities, roles);
        addAllIfNotContains(allGrantedAuthorities, permissions);

        return new UsernamePasswordAuthenticationToken(response.getPrincipal(), response.getCredentials(), allGrantedAuthorities);
    }

    /**
     * adds roles and permission if not already existent only
     * 
     * @param allGrantedAuthorities
     *            roles and permissions
     * @param rolesOrPermissions
     *            given roles or permission to be added
     */
    private void addAllIfNotContains(Collection<GrantedAuthority> allGrantedAuthorities, Collection<? extends GrantedAuthority> rolesOrPermissions) {
        for (GrantedAuthority grantedAuthority : rolesOrPermissions) {
            if (!allGrantedAuthorities.contains(grantedAuthority)) {
                allGrantedAuthorities.add(grantedAuthority);
            }
        }
    }

    public boolean supports(Class<? extends Object> authentication) {
        return this.authenticationProvider.supports(authentication);
    }

    

}
