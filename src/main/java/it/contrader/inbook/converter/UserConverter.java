package it.contrader.inbook.converter;

import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.PrivateUserDTO;
import it.contrader.inbook.dto.SigninDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.exception.RoleNotFoundException;
import it.contrader.inbook.model.Role;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.RoleRepository;
import it.contrader.inbook.repository.UserRepository;
import it.contrader.inbook.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserConverter extends AbstractConverter<User, UserDTO>{

    @Autowired
    AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder encoder;

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


    public LoggedDTO toLoggedDTO(User user){
        return user != null ?
                LoggedDTO.builder()
                        .email(user.getEmail())
                        .usertype(this.roleToUserType(user.getRoles()))
                        .jwt(jwtUtils.getRequestJwt())
                        .build()
                :null;
    }

    public User toUserDTOfromLogged(LoggedDTO loggedDTO){
        return loggedDTO != null ?
            User.builder()
                    .id(userRepository.findByEmail(loggedDTO.getEmail()).orElseThrow(()-> new NotExistException("Email non esistente")).getId())
                    .email(loggedDTO.getEmail())
                    .password(null)
                    .roles(this.userTypeToRole(loggedDTO.getUsertype()))
                    .build()
            :null;
    }


    public SigninDTO toSigninDTO(User user){
        return user != null ?
                SigninDTO.builder()
                        .email(user.getEmail())
                        .usertype(this.roleToUserType(user.getRoles()))
                        .password(user.getPassword())
                        .build()
                :null;
    }

    public UserDTO toUserfromSigninDTO(SigninDTO signinDTO){
        return signinDTO != null ?
                UserDTO.builder()
                        .email(signinDTO.getEmail())
                        .password(signinDTO.getPassword())
                        .usertype(signinDTO.getUsertype())
                        .build()
                :null;
    }

    public PrivateUserDTO toPrivate(UserDTO userDTO){
        return PrivateUserDTO.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .usertype(userDTO.getUsertype())
                .build();
    }

    public PrivateUserDTO entityToPrivate(User user){
        return PrivateUserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .usertype(roleToUserType(user.getRoles()))
                .build();
    }

    public User privateToEntity(PrivateUserDTO puDTO){
        return User.builder().id(userRepository.findByEmail(puDTO.getEmail()).get().getId())
                .email(puDTO.getEmail())
                .password(userRepository.findByEmail(puDTO.getEmail()).get().getPassword())
                .roles(userTypeToRole(puDTO.getUsertype()))
                .build();
    }

    public UserDTO privateToUserDTO(PrivateUserDTO puDTO){
        return UserDTO.builder()
                .id(userRepository.findByEmail(puDTO.getEmail()).get().getId())
                .email(puDTO.getEmail())
                .password(userRepository.findByEmail(puDTO.getEmail()).get().getPassword())
                .usertype(puDTO.getUsertype())
                .build();
    }

    public PrivateUserDTO loggedToPrivate(LoggedDTO loggedDTO){
        return PrivateUserDTO.builder()
                .id(userRepository.findByEmail(loggedDTO.getEmail()).get().getId())
                .email(loggedDTO.getEmail())
                .usertype(loggedDTO.getUsertype())
                .build();
    }
//
//    public LoggedDTO privateToLogged(PrivateUserDTO puDTO){
//        return LoggedDTO.builder()..build();
//    }


}
