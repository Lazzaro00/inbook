package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.dto.CartInsDTO;
import it.contrader.inbook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController extends AbstractController<CartDTO> {

    @Autowired
    private CartService cartService;

    @PostMapping("/whichBuyable")
    public ResponseEntity<Map<String, List<CartDTO>>> whichBuyable(@RequestBody List<CartDTO> cartDTOs) {
        return new ResponseEntity<Map<String, List<CartDTO>>>(cartService.whichBuyable(cartDTOs), HttpStatus.OK);
    }

    @PostMapping("/isBuyable")
    public ResponseEntity<Boolean> isBuyable(@RequestBody List<CartDTO> cartDTOs){
        return new ResponseEntity<Boolean>(cartService.isBuyable(cartDTOs), HttpStatus.OK);
    }

    @GetMapping("/getByUserId")
    public ResponseEntity<List<CartDTO>> getByUser_Id(@RequestParam("id")Long id){
        return new ResponseEntity<List<CartDTO>>(cartService.getByUser_Id(id), HttpStatus.OK);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<List<CartDTO>> getByUser_Email(@RequestParam("email")String email){
        return new ResponseEntity<List<CartDTO>>(cartService.getByUser_Email(email), HttpStatus.OK);
    }

    @PostMapping("/buy")
    public ResponseEntity<List<BuyDTO>> buy(@RequestBody List<CartDTO> cartDTOs){
        return  new ResponseEntity<List<BuyDTO>>(cartService.cartToBuy(cartDTOs), HttpStatus.OK);
    }

    @PostMapping("/insertCart")
    public ResponseEntity<CartDTO> insert(@RequestBody CartInsDTO cartInsDTO){
        return new ResponseEntity<CartDTO>(cartService.save(cartInsDTO), HttpStatus.OK);
    }
}
