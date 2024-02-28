package it.contrader.inbook.controller;

import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.LoginDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public ResponseEntity<LoggedDTO> login(@RequestBody @Validated LoginDTO loginDTO){
        return new ResponseEntity<LoggedDTO>(userService.login(loginDTO), HttpStatus.OK);
    }


}
