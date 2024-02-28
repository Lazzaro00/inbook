package it.contrader.inbook.controller;

import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.LoginDTO;
import it.contrader.inbook.service.AnagraphicService;
import it.contrader.inbook.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/anag")
@CrossOrigin("http://localhost:4200")
public class AnagraphicController extends AbstractController<AnagraphicDTO>{

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoggedDTO> login(@RequestBody @Validated LoginDTO loginDTO){
        return new ResponseEntity<LoggedDTO>(userService.login(loginDTO), HttpStatus.OK);
    }

}
