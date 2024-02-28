package it.contrader.inbook.repository;

import it.contrader.inbook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByRole(Role.ERole role);
}
