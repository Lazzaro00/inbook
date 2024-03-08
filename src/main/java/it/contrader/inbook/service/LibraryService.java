package it.contrader.inbook.service;

import it.contrader.inbook.converter.LibraryConverter;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.model.Book;
import it.contrader.inbook.model.Library;
import it.contrader.inbook.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        try {
//        LibraryDTO lTd = converter.toDTO(repository.getById(id));
            List<BookDTO> bTd = bookService.getByLibrary(id);

            if (!bTd.isEmpty())
                for (BookDTO b : bTd) {
                    b.setLibrary(null);
                    bookService.save(b);
                }

            repository.deleteById(id);
        }
        catch (Exception ex){
            throw  new NotExistException("Does not exist anymore");
        }

    }

    public List<LibraryDTO> getByAdminsNotNull(){
        return converter.toListDTO(repository.findByAdminsNotNull());
    }

    public List<LibraryDTO> getByCity(String city){
        return converter.toListDTO(repository.findByCity(city));
    }

    public List<BookDTO> getRelatedBook(Long bookId) {
        BookDTO focus = bookService.read(bookId);
        final int relPageSize = 25;

        List<BookDTO> booksByCategory = bookService.getByCategory(focus.getCategory());
        List<BookDTO> booksByLibraryId = bookService.getByLibrary(focus.getLibrary().getId());

        List<BookDTO> combinedResult = new ArrayList<>(booksByCategory);
        combinedResult.retainAll(booksByLibraryId);

        if (combinedResult.size() < relPageSize) {
            for (LibraryDTO lib : this.getByCity(this.read(focus.getLibrary().getId()).getCity())) {
                if (!lib.getId().equals(focus.getLibrary().getId())) { // Avoid querying the same library again
                    for (BookDTO book : bookService.getByLibrary(lib.getId())) {
                        if (book.getCategory().equals(focus.getCategory())) {
                            combinedResult.add(book);
                        }
                        if (combinedResult.size() >= relPageSize) { // Exit loop if we have enough books
                            break;
                        }
                    }
                }
                if (combinedResult.size() >= relPageSize) { // Exit loop if we have enough books
                    break;
                }
            }
        }

        return combinedResult;

    }
}
