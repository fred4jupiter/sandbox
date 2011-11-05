package de.opitzconsulting.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.opitzconsulting.demo.domain.Right;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.RoleRight;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.domain.UserRole;
import de.opitzconsulting.demo.repository.RightRepository;
import de.opitzconsulting.demo.repository.RoleRepository;
import de.opitzconsulting.demo.repository.RoleRightRepository;
import de.opitzconsulting.demo.repository.UserRepository;
import de.opitzconsulting.demo.repository.UserRoleRepository;
import de.opitzconsulting.demo.service.UserAdministration;

@Service
@Transactional(value = "sec.transactionManager")
public class DefaultUserAdministration implements UserAdministration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleRightRepository roleRightRepository;

    @Autowired
    private RightRepository rightRepository;

    @Override
    public Role createRoleWithRights(String roleStr, String... rights) {
        Role role = new Role(roleStr);
        roleRepository.save(role);

        for (String rightStr : rights) {
            Right right = new Right(rightStr);
            rightRepository.save(right);
            roleRightRepository.save(new RoleRight(role, right));
        }
        return role;
    }

    @Override
    public User createUserWithRoles(String username, String password, Role... roles) {
        User user = new User(username, password);
        userRepository.save(user);

        for (Role role : roles) {
            userRoleRepository.save(new UserRole(user, role));
        }

        return user;
    }

}
