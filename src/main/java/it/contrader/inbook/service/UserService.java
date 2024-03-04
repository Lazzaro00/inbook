package it.contrader.inbook.service;

import it.contrader.inbook.converter.AuthService;
import it.contrader.inbook.converter.UserConverter;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.exception.EmailAlreadyExistException;
import it.contrader.inbook.exception.PasswordIncorrectException;
import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.model.User;
import it.contrader.inbook.repository.UserRepository;
import it.contrader.inbook.security.JwtUtils;
import it.contrader.inbook.security.UserDetailsImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder; //TODO criptazione password

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;



    public LoggedDTO login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new NotExistException("User not exist!"));

        if (encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwt = jwtUtils.generateJwt(userDetails);
            return userConverter.toLoggedDTO(userConverter.toDTO(user), jwt);
        }

        throw new PasswordIncorrectException("The Password is incorrect!");
    }



    public LoggedDTO registration(UserDTO userDTO){
        try {
            String password = userDTO.getPassword();
            userDTO.setPassword(encoder.encode(password));
            //User newUser =
                            userRepository.save(userConverter.toEntity(userDTO));
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwt(userDetails);

            return  userConverter.toLoggedDTO(userDTO,jwt);
        }
        catch (DataIntegrityViolationException ex){
            throw new EmailAlreadyExistException("Email is already in use!");
        }
        catch (Exception ex){
            throw new RuntimeException("ERROREE:" + ex.getMessage());
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
