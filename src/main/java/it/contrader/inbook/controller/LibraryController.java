package it.contrader.inbook.controller;

import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.LibraryDTO;
import it.contrader.inbook.dto.LibraryProtectedDTO;
import it.contrader.inbook.service.LibraryService;
import it.contrader.inbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/library")
@CrossOrigin("http://localhost:4200")
public class LibraryController extends AbstractController<LibraryDTO> {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @GetMapping("/getByAdminId")
    public ResponseEntity<List<LibraryDTO>> getByAdmin(@RequestParam("adminId") long adminId) {
        return new ResponseEntity<>(libraryService.getByAdmin(adminId), HttpStatus.OK);
    }

    @GetMapping("/getByAdminEmail")
    public ResponseEntity<List<LibraryDTO>> getByAdmin(@RequestParam("email") String email){
        return new ResponseEntity<>(libraryService.getByAdmin(email), HttpStatus.OK);
    }

    @GetMapping("/getByAdminsNotNull")
    public ResponseEntity<List<LibraryDTO>> getByAdminsNotNull() {
        List<LibraryDTO> libraries = libraryService.getByAdminsNotNull();
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @GetMapping("/getRelatedBook")
    public ResponseEntity<?> getRelatedBook(@RequestParam("id")Long bookId){
        return new ResponseEntity<>(libraryService.getRelatedBook(bookId), HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<LibraryDTO> regist(@RequestBody LibraryProtectedDTO libraryProtectedDTO){
        return new ResponseEntity<LibraryDTO>(userService.libraryRegist(libraryProtectedDTO), HttpStatus.OK);
    }

    @GetMapping("/getSales")
    public ResponseEntity<List<Long>> getSales(@RequestParam("libraryId") Long libId){
        return new ResponseEntity<List<Long>>(libraryService.getSales(libId), HttpStatus.OK);
    }

    @GetMapping("/getSold")
    public ResponseEntity<List<BuyDTO>> getSold(@RequestParam("libraryId") Long libraryId){
        return new ResponseEntity<List<BuyDTO>>(libraryService.getSold(libraryId), HttpStatus.OK);
    }

}
