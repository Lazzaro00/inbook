package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin("http://localhost:4200")
public class BookController extends AbstractController<BookDTO> {

    @Autowired
    private BookService bookService;

    @GetMapping("/getByName")
    public List<BookDTO> getByName(@RequestParam("name") String name) {
        return bookService.getByName(name);
    }

    @GetMapping("/getByCategory")
    public List<BookDTO> getByCategory(@RequestParam("category") String category) {
        return bookService.getByCategory(category);
    }

    @GetMapping("/getByPrice")
    public List<BookDTO> getByPrice(@RequestParam("price") double price) {
        return bookService.getByPrice(price);
    }

    @GetMapping("/getBySerialcode")
    public List<BookDTO> getBySerialcode(@RequestParam("serialcode") String serialcode) {
        return bookService.getBySerialcode(serialcode);
    }

    @GetMapping("/getByLibrary")
    public List<BookDTO> getByLibrary(@RequestParam("libraryId") long libraryId) {
        return bookService.getByLibrary(libraryId);
    }

    @GetMapping("/countByCategory")
    public Long countByCategory(@RequestParam("category") String category) {
        return bookService.countByCategory(category);
    }

    @GetMapping("/countByPrice")
    public Long countByPrice(@RequestParam("price") double price) {
        return bookService.countByPrice(price);
    }

    @GetMapping("/countBySerialcode")
    public Long countBySerialcode(@RequestParam("serialcode") String serialcode) {
        return bookService.countBySerialcode(serialcode);
    }

    @GetMapping("/countByLibraryId")
    public Long countByLibraryId(@RequestParam("libraryId") long libraryId) {
        return bookService.countByLibrary_Id(libraryId);
    }

    @GetMapping("/countByLibraryIdAndCategory")
    public Long countByLibraryIdAndCategory(@RequestParam("libraryId") long libraryId, @RequestParam("category") String category) {
        return bookService.countByLibrary_IdAndCategory(libraryId, category);
    }

    @GetMapping("/countByLibraryIdAndSerialcode")
    public Long countByLibraryIdAndSerialcode(@RequestParam("libraryId") long libraryId, @RequestParam("serialcode") String serialcode) {
        return bookService.countByLibrary_IdAndSerialcode(libraryId, serialcode);
    }

    @GetMapping("/getByLibraryNotNull")
    public List<BookDTO> getByLibraryNotNull() {
        return bookService.getByLibraryNotNull();
    }

}
