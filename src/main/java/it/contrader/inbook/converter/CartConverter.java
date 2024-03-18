package it.contrader.inbook.converter;

import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.CartDTO;
import it.contrader.inbook.dto.CartInsDTO;
import it.contrader.inbook.model.Cart;
import it.contrader.inbook.service.BookService;
import it.contrader.inbook.service.UserService;
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

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Override
    public Cart toEntity(CartDTO cartDTO) {
        return cartDTO != null ? Cart.builder()
                .id(cartDTO.getId())
                .user(cartDTO.getUser()!=null?userConverter.privateToEntity(cartDTO.getUser()):null)
                .book(cartDTO.getBook()!=null?bookConverter.toEntity(cartDTO.getBook()):null)
                .quantity(cartDTO.getQuantitySelected())
                .build() : null;

    }

    @Override
    public CartDTO toDTO(Cart cart) {
        return cart != null ? CartDTO.builder()
                .id(cart.getId())
                .user(cart.getUser()!=null?userConverter.entityToPrivate(cart.getUser()):null)
                .book(cart.getBook()!=null?bookConverter.toDTO(cart.getBook()):null)
                .quantitySelected(cart.getQuantity())
                .build() : null;
    }

    public BuyDTO toBuyDTO(CartDTO cartDTO){
        return cartDTO != null ? BuyDTO.builder()
                .user(cartDTO.getUser())
                .book(cartDTO.getBook())
                .quantity(cartDTO.getQuantitySelected())
                .date(LocalDate.now())
                .build() : null;
    }

    public List<BuyDTO> toBuyDTOList(List<CartDTO> cartDTOList){
        return cartDTOList.stream().map(this::toBuyDTO).collect(Collectors.toList());
    }

    public CartDTO CartInsToCart(CartInsDTO cartIns){
        return CartDTO.builder()
                .user(userConverter.toPrivate(userService.getByEmail(cartIns.getUserMail())))
                .book(bookService.read(cartIns.getBookId()))
                .quantitySelected(cartIns.getQuantity())
                .build();
    }
}
