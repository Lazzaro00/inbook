package it.contrader.inbook.controller;

import it.contrader.inbook.dto.LibraryDTO;
import it.contrader.inbook.service.LibraryService;
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

    @GetMapping("/getByAdminId")
    public ResponseEntity<List<LibraryDTO>> getByAdminId(@RequestParam("adminId") long adminId) {
        List<LibraryDTO> libraries = libraryService.getByAdmin_Id(adminId);
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @GetMapping("/getByAdminsNotNull")
    public ResponseEntity<List<LibraryDTO>> getByAdminsNotNull() {
        List<LibraryDTO> libraries = libraryService.getByAdminsNotNull();
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }


}
