package it.contrader.inbook.converter;


import it.contrader.inbook.model.Role;
import it.contrader.inbook.repository.RoleRepository;
import it.contrader.inbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public  Set<Role> createRoles(String rolesString) {
        if (rolesString != null && !rolesString.isEmpty()) {
            Set<Role> set = new HashSet<>();
                if (rolesString.equalsIgnoreCase("ADMIN")) {
                    set.add(roleRepository.findByRole(Role.ERole.ROLE_ADMIN)
                            .orElseGet(() ->{
                                        Role role1 = new Role();
                                        role1.setRole(Role.ERole.ROLE_ADMIN);
                                        roleRepository.save(role1);
                                        return role1;
                                    }
                            ));
                } else {
                    set.add(roleRepository.findByRole(Role.ERole.ROLE_USER)
                            .orElseGet(() ->{
                                        Role role1 = new Role();
                                        role1.setRole(Role.ERole.ROLE_USER);
                                        roleRepository.save(role1);
                                        return role1;
                                    }
                            ));
                }
            return set;
        } else {
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByRole(Role.ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
            return roles;
        }
    }
}