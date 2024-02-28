package it.contrader.inbook.service;

import it.contrader.inbook.controller.BookController;
import it.contrader.inbook.converter.BookConverter;
import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.model.Book;
import it.contrader.inbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends AbstractService<Book, BookDTO> {

    @Autowired
    BookRepository repository;

    @Autowired
    BookConverter converter;

    public List<BookDTO> getByName(String name){
        return converter.toListDTO(repository.findByName(name));
    }
}