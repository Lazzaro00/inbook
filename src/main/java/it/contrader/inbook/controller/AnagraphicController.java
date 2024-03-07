package it.contrader.inbook.controller;

import it.contrader.inbook.converter.AnagraphicConverter;
import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.LoggedDTO;
import it.contrader.inbook.dto.LoginDTO;
import it.contrader.inbook.exception.InvalidGenderException;
import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.repository.AnagraphicRepository;
import it.contrader.inbook.service.AnagraphicService;
import it.contrader.inbook.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/anag")
@CrossOrigin("http://localhost:4200")
public class AnagraphicController extends AbstractController<AnagraphicDTO>{

    @Autowired
    AnagraphicConverter converter;

    @Autowired
    AnagraphicRepository repository;


    public AnagraphicDTO getByUserId(long userId){
        Anagraphic anagraphic = repository.findByUser_Id(userId).orElse(null);
        return anagraphic != null ? converter.toDTO(anagraphic) : null;
    }

    public Long countByBirthDateStartingFrom(int year){
        if (String.valueOf(year).length() != 4 || year > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Anno non valido");
        }
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        return repository.countByBirthDateStartingFrom(firstDayOfYear);
    }

    public Long countByGender(String genderString){
        try {
            Anagraphic.Gender gender = Anagraphic.Gender.valueOf(genderString.toUpperCase());
            return repository.countByGender(gender);
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException("Valore di genere non valido: " + genderString);
        }
    }

    public Long countByNationality(String nationality){
        return repository.countByNationality(nationality);
    }

    public Long countByProvince(String province){
        return repository.countByProvince(province);
    }

    public Long countByCity(String city){
        return repository.countByCity(city);
    }

    public Long countByAddress(String address){
        return repository.countByAddress(address);
    }

    public List<AnagraphicDTO> getByUserNotNull(){
        return converter.toListDTO(repository.findByUserNotNull());
    }



}
