package de.opitzconsulting.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.repository.UserRepository;
import de.opitzconsulting.demo.repository.UserRoleRepository;

public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(userRepository);
        Assert.notNull(userRoleRepository);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user with username " + username + " could not be found.");
        }

        List<Role> userRoles = userRoleRepository.findByUser(user);
        Collection<GrantedAuthority> auths = mapToAuths(userRoles);
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), true, true, true, true, auths);
    }

    private Collection<GrantedAuthority> mapToAuths(List<Role> userRoles) {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

        for (Role role : userRoles) {
            auths.add(new GrantedAuthorityImpl(role.getName()));
        }
        return auths;
    }
        
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public void setUserRoleRepository(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }
}
