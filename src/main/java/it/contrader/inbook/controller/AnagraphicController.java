package it.contrader.inbook.controller;

import it.contrader.inbook.dto.AnagraphicDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anag")
@CrossOrigin("http://localhost:4200")
public class AnagraphicController extends AbstractController<AnagraphicDTO>{

}
