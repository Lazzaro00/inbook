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
    public Long countByRoles(Set<Role> roles);
    List<User> findByRoles(Set<Role> roles);


}
