package it.contrader.inbook.service;

import it.contrader.inbook.converter.CartConverter;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.dto.CartInsDTO;
import it.contrader.inbook.model.Cart;
import it.contrader.inbook.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService extends AbstractService<Cart, CartDTO> {

    @Autowired
    CartConverter cartConverter;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BuyService buyService;

    public Map<String, List<CartDTO>> whichBuyable(List<CartDTO> cartDTOs) {
        if (cartDTOs != null && !cartDTOs.isEmpty()) {
            List<CartDTO> buyables = new ArrayList<>();
            List<CartDTO> notBuyables = new ArrayList<>();

            cartDTOs.stream().peek(cartDTO -> {
                    int av = bookService.read(cartDTO.getBook().getId()).getQuantity();

                    av -= buyService.getByBook_Id(cartDTO.getBook().getId()).stream()
                            .mapToInt(BuyDTO::getQuantity)
                            .sum();

                    if (av < cartDTO.getQuantitySelected()) {
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

    public boolean isBuyable(List<CartDTO> cartDTOs) {
        if (cartDTOs != null && !cartDTOs.isEmpty()) {
            for (CartDTO cartDTO : cartDTOs) {
                int av = bookService.read(cartDTO.getBook().getId()).getQuantity();

                av -= buyService.getByBook_Id(cartDTO.getBook().getId()).stream()
                        .mapToInt(BuyDTO::getQuantity)
                        .sum();

                if (av < cartDTO.getQuantitySelected()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public List<CartDTO> getByUser_Id(Long user_Id){
        return cartConverter.toListDTO(cartRepository.findByUser_Id(user_Id));
    }

    public List<CartDTO> getByUser_Email(String user_email){
        return cartConverter.toListDTO(cartRepository.findByUser_Email(user_email));
    }

    public List<BuyDTO> cartToBuy(List<CartDTO> cartDTOs)  {
        if (cartDTOs != null && !cartDTOs.isEmpty()) {
            if (this.isBuyable(cartDTOs)) {
                List<BuyDTO> buys = new ArrayList<>();
                cartDTOs.stream().peek(cartDTO -> {
                    buys.add(buyService.save(cartConverter.toBuyDTO(cartDTO)));
                    if (this.read(cartDTO.getId()) != null) {
                        this.delete(cartDTO.getId());
                    }
                });
                return buys;
            } else return null; //TODO da gestire con un eccezione
        } else return null;
    }

    public CartDTO save(CartInsDTO cartInsDTO) {
        CartDTO c = cartConverter.CartInsToCart(cartInsDTO);

        Optional<CartDTO> exCartO = this.getAll().stream()
                .filter(cart -> cart.equals(c))
                .findFirst();

        if (exCartO.isPresent()) {
            CartDTO exCart = exCartO.get();
            exCart.setQuantitySelected(exCart.getQuantitySelected() + cartInsDTO.getQuantity());
            return this.save(exCart);
        } else {
            return this.save(c);
        }
    }

}
