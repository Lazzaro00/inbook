package it.contrader.inbook.converter;

import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.model.Cart;
import it.contrader.inbook.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;

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
}
