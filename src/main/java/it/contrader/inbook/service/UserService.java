package it.contrader.inbook.service;

import it.contrader.inbook.converter.UserConverter;
import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.LoginDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.RoleRepository;
import it.contrader.inbook.repository.UserRepository;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService extends AbstractService<User, UserDTO>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;



    public LoggedDTO login(LoginDTO loginDTO){
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new RuntimeException("L'utente non esiste"));
        if(loginDTO.getPassword().equals(user.getPassword())){
            return userConverter.toLoggedDTO(userConverter.toDTO(user));
        }
        throw new RuntimeException("La password non è corretta");
    }



    public LoggedDTO registration(UserDTO userDTO){
        try {
            return userConverter.toLoggedDTO(userConverter.toDTO(userRepository.save(userConverter.toEntity(userDTO))));
        }
        catch (Exception ex){
            throw new RuntimeException("Email già in uso");
        }

    }
}
