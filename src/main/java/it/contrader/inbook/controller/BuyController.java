package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BuyDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buy")
@CrossOrigin("http://localhost:4200")
public class BuyController extends AbstractController<BuyDTO>{
}
