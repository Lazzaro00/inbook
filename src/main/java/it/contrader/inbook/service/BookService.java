package it.contrader.inbook.service;

import it.contrader.inbook.controller.BookController;
import it.contrader.inbook.converter.BookConverter;
import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.dto.BuyDTO;
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

    @Autowired
    BuyService buyService;

    public List<BookDTO> getByName(String name){
        return converter.toListDTO(repository.findByName(name));
    }

    public List<BookDTO> getByCategory(String category){
        return converter.toListDTO(repository.findByCategory(category));
    }

    public List<BookDTO> getByPrice(double price){
        return converter.toListDTO(repository.findByPriceGreaterThan(price));
    }

    public List<BookDTO> getBySerialcode(String serialcode){
        return converter.toListDTO(repository.findBySerialcode(serialcode));
    }

    public List<BookDTO> getByLibrary(long library_Id){
        return converter.toListDTO(repository.findByLibrary_Id(library_Id));
    }

    public void delete(Long id){
        List<BuyDTO> byTd = buyService.getByBook_Id(id);

        if(!byTd.isEmpty())
            for (BuyDTO b : byTd){
                b.setBook(null);
                buyService.save(b);
            }

        repository.deleteById(id);
    }
}