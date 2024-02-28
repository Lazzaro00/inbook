package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BookDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200")
public class BookController extends AbstractController<BookDTO>{

}
