package it.contrader.inbook.converter;

import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.model.Cart;
import it.contrader.inbook.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter extends AbstractConverter<Cart, CartDTO> {
    @Autowired
    BookConverter bookConverter;

    @Autowired
    UserConverter userConverter;

    @Override
    public Cart toEntity(CartDTO cartDTO) {
        return cartDTO != null ? Cart.builder()
                .id(cartDTO.getId())
                .user(userConverter.toEntity(cartDTO.getUser()))
                .book(bookConverter.toEntity(cartDTO.getBook()))
                .book(bookConverter.toEntity(cartDTO.getBook()))
                .quantity(cartDTO.getQuantity())
                .build() : null;

    }

    @Override
    public CartDTO toDTO(Cart cart) {
        return cart != null ? CartDTO.builder()
                .id(cart.getId())
                .user(userConverter.toDTO(cart.getUser()))
                .book(bookConverter.toDTO(cart.getBook()))
                .quantity(cart.getQuantity())
                .build() : null;
    }

    public BuyDTO toBuyDTO(CartDTO cartDTO){
        return cartDTO != null ? BuyDTO.builder()
                .user(cartDTO.getUser())
                .book(cartDTO.getBook())
                .quantity(cartDTO.getQuantity())
                .date(LocalDate.now())
                .build() : null;
    }

    public List<BuyDTO> toBuyDTOList(List<CartDTO> cartDTOList){
        return cartDTOList.stream().map(this::toBuyDTO).collect(Collectors.toList());
    }
}
