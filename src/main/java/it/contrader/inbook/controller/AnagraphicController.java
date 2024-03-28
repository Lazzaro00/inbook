package it.contrader.inbook.controller;

import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.AnagraphicRegistrationDTO;
import it.contrader.inbook.dto.AnagraphicUpdateDTO;
import it.contrader.inbook.exception.InvalidGenderException;
import it.contrader.inbook.exception.YearNotValidException;
import it.contrader.inbook.service.AnagraphicService;
import it.contrader.inbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/anag")
@CrossOrigin("*")
public class AnagraphicController extends AbstractController<AnagraphicDTO> {

    @Autowired
    AnagraphicService anagraphicService;

    @Autowired
    UserService userService;

    @GetMapping("/getByUserId")
    public ResponseEntity<AnagraphicDTO> getByUserId(@RequestParam("id") Long userId) {
        AnagraphicDTO anagraphicDTO = anagraphicService.getByUser(userId);
        return ResponseEntity.ok(anagraphicDTO);
    }

    @GetMapping("/getByUserEmail")
    public ResponseEntity<AnagraphicDTO> getByUserEmail(@RequestParam("email") String email) {
        AnagraphicDTO anagraphicDTO = anagraphicService.getByUser(email);
        return ResponseEntity.ok(anagraphicDTO);
    }

    @GetMapping("/countByYear")
    public ResponseEntity<Long> countByBirthDateStartingFrom(@RequestParam("year") int year) {
        if (String.valueOf(year).length() != 4 || year > LocalDate.now().getYear()) {
            throw new YearNotValidException("Anno non valido");
        } //todo se presente nel service, questo controllo è ridondante
        return ResponseEntity.ok(anagraphicService.countByBirthDateStartingFrom(year));
    }

    @GetMapping("/countByGender")
    public ResponseEntity<Long> countByGender(@RequestParam("gender") String genderString) {
        try {
            Long count = anagraphicService.countByGender(genderString);
            return ResponseEntity.ok(count);
        } catch (IllegalArgumentException e) {  //todo se presente nel service, questo controllo è ridondante
            throw new InvalidGenderException("Valore di genere non valido: " + genderString);
        }
    }

    @GetMapping("/countByNationality")
    public ResponseEntity<Long> countByNationality(@RequestParam("nationality") String nationality) {
        Long count = anagraphicService.countByNationality(nationality);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countByProvince")
    public ResponseEntity<Long> countByProvince(@RequestParam("province") String province) {
        Long count = anagraphicService.countByProvince(province);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countByCity")
    public ResponseEntity<Long> countByCity(@RequestParam("city") String city) {
        Long count = anagraphicService.countByCity(city);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/countByAddress")
    public ResponseEntity<Long> countByAddress(@RequestParam("address") String address) {
        Long count = anagraphicService.countByAddress(address);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/getByUserNotNull")
    public ResponseEntity<List<AnagraphicDTO>> getByUserNotNull() {
        List<AnagraphicDTO> anagraphicDTOList = anagraphicService.getByUserNotNull();
        return ResponseEntity.ok(anagraphicDTOList);
    }

    @PutMapping("/save")
    public ResponseEntity<?> save(@RequestBody AnagraphicUpdateDTO auDTO){
        return new ResponseEntity<>(userService.save(auDTO), HttpStatus.OK) ;
    }
}
