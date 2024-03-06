package it.contrader.inbook.service;

import it.contrader.inbook.converter.AnagraphicConverter;
import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.exception.InvalidGenderException;
import it.contrader.inbook.exception.YearNotValidException;
import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.repository.AnagraphicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnagraphicService extends AbstractService<Anagraphic, AnagraphicDTO> {

    @Autowired
    AnagraphicConverter converter;

    @Autowired
    AnagraphicRepository repository;

    public AnagraphicDTO getByUserId(long userId){
        Anagraphic an = repository.findByUser_Id(userId).orElse(null);
        return an != null ? converter.toDTO(an) : null;
    }

    public Long countByBirthDateStartingFrom(int year){
        if (String.valueOf(year).length() != 4 || year > LocalDate.now().getYear()) {
            throw new YearNotValidException("Anno non valido");
        }
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);
        return repository.countByBirthDateStartingFrom(firstDayOfYear);
    }

    public Long countByGender(String genderString){
        try {
            Anagraphic.Gender gender = Anagraphic.Gender.valueOf(genderString.toUpperCase()); // Converti la stringa in un Gender
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
