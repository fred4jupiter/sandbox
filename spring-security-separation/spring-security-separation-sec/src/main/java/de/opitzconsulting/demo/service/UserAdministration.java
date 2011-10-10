package de.opitzconsulting.demo.service;

import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.User;

public interface UserAdministration {

    Role createRoleWithRights(String role, String... rights);

    User createUserWithRoles(String username, String password, Role... roles);
}
