package it.contrader.inbook.controller;

import it.contrader.inbook.dto.*;
import it.contrader.inbook.service.AnagraphicService;
import it.contrader.inbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{

    @Autowired
    private UserService userService;

    @Autowired
    private AnagraphicService anagraphicService;

    @PostMapping("/login")
    public ResponseEntity<LoggedDTO> login(@RequestBody @Validated LoginDTO loginDTO){
        return new ResponseEntity<LoggedDTO>(userService.login(loginDTO), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<LoggedDTO> registration(@RequestBody @Validated UserDTO userDTO){
        return new ResponseEntity<LoggedDTO>(userService.registration(userDTO), HttpStatus.OK);
    }

    @PostMapping("/registrationAnag")
    public ResponseEntity<AnagraphicDTO> registrationAnag(@RequestBody @Validated AnagraphicRegistrationDTO anagraphicRegistrationDTO){
        return new ResponseEntity<AnagraphicDTO>(userService.saveAnag(anagraphicRegistrationDTO), HttpStatus.OK);
    }

    @GetMapping("/getAllPaginata")
    public ResponseEntity<Page<AnagraphicDTO>> getallpaginata(@RequestParam("pageNumber") int pageNum, @RequestParam("pageSize") int pageSize){
        return new ResponseEntity<Page<AnagraphicDTO>>(anagraphicService.getallpaginata(pageNum, pageSize), HttpStatus.OK);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam("email") String email){
        return new ResponseEntity<UserDTO>(userService.getByEmail(email), HttpStatus.OK);
    }

}
