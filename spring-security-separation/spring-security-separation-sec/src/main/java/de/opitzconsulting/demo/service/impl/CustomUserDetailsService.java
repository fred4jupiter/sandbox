package de.opitzconsulting.demo.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.repository.UserRepository;
import de.opitzconsulting.demo.repository.UserRoleRepository;

@Service("customUserDetailsService")
@Transactional(value = "sec.transactionManager")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(userRepository);
        Assert.notNull(userRoleRepository);

        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("No username given.");
        }

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
}
