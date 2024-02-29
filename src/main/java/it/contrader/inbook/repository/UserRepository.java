package it.contrader.inbook.repository;

import it.contrader.inbook.model.Role;
import it.contrader.inbook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {

    public Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u JOIN u.roles r WHERE r IN :roles")
    Long countByRoles(@Param("roles") Set<Role> roles);

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r IN :roles")
    List<User> findByRoles(@Param("roles") Set<Role> roles);


}
