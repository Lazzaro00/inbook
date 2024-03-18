package it.contrader.inbook.converter;

import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.model.Book;

import it.contrader.inbook.model.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyConverter extends AbstractConverter<Buy, BuyDTO> {

    @Autowired
    BookConverter bookConverter;

    @Autowired
    UserConverter userConverter;

    @Override
    public Buy toEntity(BuyDTO buyDTO) {
        return buyDTO != null ? Buy.builder()
                .id(buyDTO.getId())
                .user(buyDTO.getUser()!=null?userConverter.privateToEntity(buyDTO.getUser()):null)
                .book(buyDTO.getBook()!=null?bookConverter.toEntity(buyDTO.getBook()):null)
                .date(buyDTO.getDate())
                .quantity(buyDTO.getQuantity())
                .orderNum(buyDTO.getOrderNum())
                .build() : null;
    }

    @Override
    public BuyDTO toDTO(Buy buy) {
        return buy != null ? BuyDTO.builder()
                .id(buy.getId())
                .user(buy.getUser()!=null?userConverter.entityToPrivate(buy.getUser()):null)
                .book(buy.getBook()!=null?bookConverter.toDTO(buy.getBook()):null)
                .date(buy.getDate())
                .quantity(buy.getQuantity())
                .orderNum(buy.getOrderNum())
                .build() : null;
    }
}
