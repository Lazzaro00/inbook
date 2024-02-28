package it.contrader.inbook.converter;

import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.model.Role;
import it.contrader.inbook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserConverter extends AbstractConverter<User, UserDTO>{


    private Set<Role> userTypeToRole(String usertype){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.builder().role(Role.ERole.valueOf(usertype)).build());
        return roleSet;
    }

    private String roleToUserType(Set<Role> roles){
        String usertype = null;
        if(roles.contains(new Role(2, Role.ERole.ROLE_ADMIN))) {      //(ERole.ROLE_ADMIN.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
            usertype = "ADMIN";
        } else if (Role.ERole.ROLE_USER.name().equals(((Role)roles.toArray()[0]).getRole().name())) {
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
                .roles(userTypeToRole(userDTO.getUsertype()))
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


    public LoggedDTO toLoggedDTO(UserDTO userDTO){
        return userDTO != null ?
                LoggedDTO.builder()
                        .email(userDTO.getEmail())
                        .usertype(userDTO.getUsertype())
                        .build()
                :null;
    }



}
