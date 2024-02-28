package it.contrader.inbook.converter;

import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookConverter extends AbstractConverter<Book, BookDTO> {

    @Autowired
    LibraryConverter libraryConverter;

    @Override
    public Book toEntity(BookDTO bookDTO) {
       return bookDTO != null ? Book.builder()
                .id(bookDTO.getId())
                .name(bookDTO.getName())
                .images(bookDTO.getImages())
                .category(bookDTO.getCategory())
                .description(bookDTO.getDescription())
                .quantity(bookDTO.getQuantity())
                .serialcode(bookDTO.getSerialcode())
                .price(bookDTO.getPrice())
                .library(libraryConverter.toEntity(bookDTO.getLibrary()))
                .build() : null;

    }

    @Override
    public BookDTO toDTO(Book book) {
        return book!=null ? BookDTO.builder()
                .id(book.getId())
                .name(book.getName())
                .images(book.getImages())
                .category(book.getCategory())
                .description(book.getDescription())
                .quantity(book.getQuantity())
                .serialcode(book.getSerialcode())
                .price(book.getPrice())
                .library(libraryConverter.toDTO(book.getLibrary()))
                .build():null;
    }
}
