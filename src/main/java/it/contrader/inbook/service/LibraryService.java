package it.contrader.inbook.service;

import it.contrader.inbook.converter.LibraryConverter;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.model.Library;
import it.contrader.inbook.repository.BookRepository;
import it.contrader.inbook.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService extends AbstractService<Library, LibraryDTO>{

    @Autowired
    LibraryConverter converter;

    @Autowired
    LibraryRepository repository;

    @Autowired
    BookService bookService;

    public List<LibraryDTO> getByAdmin_Id(long adminId){
        return converter.toListDTO(repository.findByAdmins_Id(adminId));
    }

    @Override
    public void delete(Long id){
//        LibraryDTO lTd = converter.toDTO(repository.getById(id));
        List<BookDTO> bTd = bookService.getByLibrary(id);

        if(!bTd.isEmpty())
            for (BookDTO b : bTd){
                b.setLibrary(null);
                bookService.save(b);
            }

        repository.deleteById(id);
    }

    public List<LibraryDTO> getByAdminsNotNull(){
        return converter.toListDTO(repository.findByAdminsNotNull());
    }
}
