package de.opitzconsulting.demo.repository;

import java.util.List;

import de.opitzconsulting.demo.domain.Right;
import de.opitzconsulting.demo.domain.Role;
import de.opitzconsulting.demo.domain.RoleRight;

public interface RoleRightRepository{

    List<Right> findByRole(Role role);
    
    RoleRight save(RoleRight roleRight);
}
