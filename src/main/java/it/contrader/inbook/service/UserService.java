package it.contrader.inbook.service;

import it.contrader.inbook.converter.AuthService;
import it.contrader.inbook.converter.UserConverter;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.exception.EmailAlreadyExistException;
import it.contrader.inbook.exception.PasswordIncorrectException;
import it.contrader.inbook.exception.UserNotExistException;
import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.RoleRepository;
import it.contrader.inbook.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService extends AbstractService<User, UserDTO>{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AnagraphicService anagraphicService;

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private BuyService buyService;



    public LoggedDTO login(LoginDTO loginDTO){
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new UserNotExistException("User not exist!"));
        if(loginDTO.getPassword().equals(user.getPassword())){
            return userConverter.toLoggedDTO(userConverter.toDTO(user));
        }
        throw new PasswordIncorrectException("The Password is incorrect!");
    }



    public LoggedDTO registration(UserDTO userDTO){
        try {
            return userConverter.toLoggedDTO(userConverter.toDTO(userRepository.save(userConverter.toEntity(userDTO))));
        }
        catch (Exception ex){
            throw new EmailAlreadyExistException("Email is already in use!");
        }

    }

    @Override
    public void delete(Long id){
        UserDTO uTd = userConverter.toDTO(userRepository.getById(id));
        AnagraphicDTO aTd = anagraphicService.getByUserId(id);
        List<LibraryDTO> lTd = libraryService.getByAdmin_Id(id);
        List<BuyDTO> bTd = buyService.getByUser_Id(id);

        aTd.setUser(null);
        anagraphicService.save(aTd);

        if (!lTd.isEmpty())
        for (LibraryDTO l : lTd) {
          l.getAdmins().remove(uTd);
          if (l.getAdmins().isEmpty())
              l.setAdmins(null);
          libraryService.save(l);
        }

        if(!bTd.isEmpty())
        for (BuyDTO b : bTd){
            b.setUser(null);
            buyService.save(b);
        }

        userRepository.deleteById(id);
    }

    public Long countByAdmins(){
        return userRepository.countByRoles(authService.createRoles("ADMIN"));
    }

    public Long countByUsers(){
        return userRepository.countByRoles(authService.createRoles("USER"));
    }

    public List<UserDTO> getByUsertype(String usertype){
        return userConverter.toListDTO(userRepository.findByRoles(authService.createRoles(usertype)));
    }
}
