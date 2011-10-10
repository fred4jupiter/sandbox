package de.opitzconsulting.demo.repository;

import de.opitzconsulting.demo.domain.Role;

public interface RoleRepository{

    Role findByName(String name);

    Role save(Role role);
}
