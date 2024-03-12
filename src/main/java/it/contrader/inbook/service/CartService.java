package it.contrader.inbook.service;

import it.contrader.inbook.converter.CartConverter;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.model.Cart;
import it.contrader.inbook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService extends AbstractService<Cart, CartDTO> {

    @Autowired
    CartConverter cartConverter;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BuyService buyService;

    public Map<String, List<CartDTO>> isCartBuyable(List<CartDTO> cartDTOs) {
        if (cartDTOs != null && !cartDTOs.isEmpty()) {
            List<CartDTO> buyables = new ArrayList<>();
            List<CartDTO> notBuyables = new ArrayList<>();

            cartDTOs.stream().peek(cartDTO -> {
                    int av = bookService.read(cartDTO.getBook().getId()).getQuantity();

                    av -= buyService.getByBook_Id(cartDTO.getBook().getId()).stream()
                            .mapToInt(BuyDTO::getQuantity)
                            .sum();

                    if (av < cartDTO.getQuantity()) {
                        notBuyables.add(cartDTO);
                    } else {
                        buyables.add(cartDTO);
                    }
            });

            Map<String, List<CartDTO>> result = new HashMap<>();
            result.put("buyables", buyables);
            result.put("notBuyables", notBuyables);
            return result;
        } else {
            return null;
        }
    }

    public List<CartDTO> getByUser_Id(Long user_Id){
        return cartConverter.toListDTO(cartRepository.findByUser_Id(user_Id));
    }

}
