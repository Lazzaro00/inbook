package it.contrader.inbook.controller;

import it.contrader.inbook.dto.LibraryDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lib")
@CrossOrigin("http://localhost:4200")
public class LibraryController extends AbstractController<LibraryDTO>{
}
