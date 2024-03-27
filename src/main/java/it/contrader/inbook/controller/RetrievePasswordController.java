package it.contrader.inbook.controller;

import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.AnagraphicRegistrationDTO;
import it.contrader.inbook.dto.AnagraphicUpdateDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.exception.InvalidGenderException;
import it.contrader.inbook.exception.YearNotValidException;
import it.contrader.inbook.service.AnagraphicService;
import it.contrader.inbook.service.RetrievePasswordService;
import it.contrader.inbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/retpass")
@CrossOrigin("http://localhost:4200")
public class RetrievePasswordController {


    @Autowired
    RetrievePasswordService rpService;

    @GetMapping("/changePass")
    public String changePassword(@RequestParam("email") String email){
        return rpService.changePassword(email);
    }

    @GetMapping("/confirm")
    public UserDTO confirm(@RequestParam("generatedCode") String generatedCode, @RequestParam("userCode") String userCode, @RequestParam("newPass") String newPass, @RequestParam("email") String email){
        return rpService.confirmCode(generatedCode, userCode, newPass, email);
    }
}
