package it.contrader.inbook.controller;

import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;

public class CartController extends AbstractController<CartDTO> {

    @Autowired
    private CartService cartService;

    @PostMapping("/isCartBuyable")
    public ResponseEntity<Map<String, List<CartDTO>>> isCartBuyable(@RequestBody List<CartDTO> cartDTOs) {
        return new ResponseEntity<Map<String, List<CartDTO>>>(cartService.isCartBuyable(cartDTOs), HttpStatus.OK);
    }

    @GetMapping("/getByUser")
    public ResponseEntity<List<CartDTO>> getByUser(@RequestParam("id")Long id){
        return new ResponseEntity<List<CartDTO>>(cartService.getByUser_Id(id), HttpStatus.OK);
    }
}
