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
                .user(userConverter.privateToEntity(buyDTO.getUser()))
                .book(bookConverter.toEntity(buyDTO.getBook()))
                .date(buyDTO.getDate())
                .quantity(buyDTO.getQuantity())
                .orderNum(buyDTO.getOrderNum())
                .build() : null;
    }

    @Override
    public BuyDTO toDTO(Buy buy) {
        return buy != null ? BuyDTO.builder()
                .id(buy.getId())
                .user(userConverter.entityToPrivate(buy.getUser()))
                .book(bookConverter.toDTO(buy.getBook()))
                .date(buy.getDate())
                .quantity(buy.getQuantity())
                .orderNum(buy.getOrderNum())
                .build() : null;
    }
}
