package it.contrader.inbook.converter;

import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.exception.RoleNotFoundException;
import it.contrader.inbook.model.Role;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO>{

    @Autowired
    AuthService authService;

    @Autowired
    private RoleRepository roleRepository;


    private Set<Role> userTypeToRole(String usertype){
        return authService.createRoles(usertype);
    }

    public String roleToUserType(Set<Role> roles){
        String usertype = null;
        if(roles.contains(roleRepository.findByRole(Role.ERole.ROLE_ADMIN).orElseThrow(() ->new RoleNotFoundException("Role not found!")))) {      //(ERole.ROLE_ADMIN.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
            usertype = "ADMIN";
        } else if (roles.contains(roleRepository.findByRole(Role.ERole.ROLE_USER).orElseThrow(() ->new RoleNotFoundException("Role not found!")))) {
            usertype = "USER";
        }
        return  usertype;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return userDTO!=null? User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(userDTO.getUsertype()!=null? userTypeToRole(userDTO.getUsertype()):null)
                .build():null;
    }

    @Override
    public UserDTO toDTO(User user) {
         return user!=null? UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .usertype(roleToUserType(user.getRoles()))
                .build():null;
    }


    public LoggedDTO toLoggedDTO(UserDTO userDTO, String jwt){
        return userDTO != null ?
                LoggedDTO.builder()
                        .email(userDTO.getEmail())
                        .usertype(userDTO.getUsertype())
                        .jwt(jwt)
                        .build()
                :null;
    }



}
