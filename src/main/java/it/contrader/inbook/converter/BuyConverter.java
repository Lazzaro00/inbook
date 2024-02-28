package it.contrader.inbook.converter;

import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.model.Book;

import it.contrader.inbook.model.Buy;
import org.springframework.beans.factory.annotation.Autowired;


public class BuyConverter extends AbstractConverter<Buy, BuyDTO> {

    @Autowired
    BookConverter bookConverter;

    @Autowired
    UserConverter userConverter;

    @Override
    public Buy toEntity(BuyDTO buyDTO) {
        return buyDTO != null ? Buy.builder()
                .id(buyDTO.getId())
                .user(userConverter.toEntity(buyDTO.getUser()))
                .book(bookConverter.toEntity(buyDTO.getBook()))
                .date(buyDTO.getDate())
                .build() : null;

    }

    @Override
    public BuyDTO toDTO(Buy buy) {
        return buy != null ? BuyDTO.builder()
                .id(buy.getId())
                .user(userConverter.toDTO(buy.getUser()))
                .book(bookConverter.toDTO(buy.getBook()))
                .date(buy.getDate())
                .build() : null;
    }
}
