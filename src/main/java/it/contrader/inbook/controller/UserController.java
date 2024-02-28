package it.contrader.inbook.controller;

import it.contrader.inbook.dto.UserDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class UserController extends AbstractController<UserDTO>{


}
