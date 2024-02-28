package it.contrader.inbook.service;

import it.contrader.inbook.converter.UserConverter;
import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.LoginDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

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
}
