package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buy")
@CrossOrigin("*")
public class BuyController extends AbstractController<BuyDTO> {

    @Autowired
    private BuyService buyService;

    @GetMapping("/getByUserAndBook")
    public List<BuyDTO> getByUserAndBook(@RequestParam("userId") long userId, @RequestParam("bookId") long bookId) {
        return buyService.getByUser_IdAndBook_Id(userId, bookId);
    }

    @GetMapping("/getByUser")
    public List<BuyDTO> getByUser(@RequestParam("userId") long userId) {
        return buyService.getByUser_Id(userId);
    }

    @GetMapping("/getByBook")
    public List<BuyDTO> getByBook(@RequestParam("bookId") long bookId) {
        return buyService.getByBook_Id(bookId);
    }

    @GetMapping("/getAllNotNull")
    public List<BuyDTO> getAllNotNull() {
        return buyService.getAllNotNull();
    }

}
