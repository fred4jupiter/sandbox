package de.opitzconsulting.demo.repository;

import java.util.List;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;
import de.opitzconsulting.demo.domain.UserRole;

public interface UserRoleRepository {

    List<Role> findByUser(User user);

    UserRole save(UserRole userRole);
}
