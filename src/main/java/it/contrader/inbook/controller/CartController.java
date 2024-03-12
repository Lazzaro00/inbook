package it.contrader.inbook.controller;

import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@CrossOrigin("http://localhost:4200")
public class CartController extends AbstractController<CartDTO> {

    @Autowired
    private CartService cartService;

    @PostMapping("/boolBuyable")
    public ResponseEntity<Map<String, List<CartDTO>>> whichBuyable(@RequestBody List<CartDTO> cartDTOs) {
        return new ResponseEntity<Map<String, List<CartDTO>>>(cartService.whichBuyable(cartDTOs), HttpStatus.OK);
    }

    @PostMapping("/isBuyable")
    public ResponseEntity<Boolean> isBuyable(@RequestBody List<CartDTO> cartDTOs){
        return new ResponseEntity<Boolean>(cartService.isBuyable(cartDTOs), HttpStatus.OK);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<List<CartDTO>> getByUser(@RequestParam("id")Long id){
        return new ResponseEntity<List<CartDTO>>(cartService.getByUser_Id(id), HttpStatus.OK);
    }
}
