package de.opitzconsulting.demo.repository;

import de.opitzconsulting.demo.domain.User;

public interface UserRepository {

    User findByUsername(String username);

    User save(User user);
}
