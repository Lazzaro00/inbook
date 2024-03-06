package it.contrader.inbook.converter;

import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.exception.InvalidGenderException;
import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnagraphicConverter extends AbstractConverter<Anagraphic, AnagraphicDTO> {

    @Autowired
    UserConverter userConverter;

    private String genderToString(Anagraphic.Gender gender) {
        return gender != null ? gender.name() : null;
    }

    private Anagraphic.Gender stringToGender(String gender) {
        if (gender == null) {
            return null;
        }
        try {
            return Anagraphic.Gender.valueOf(gender.toUpperCase());
        } catch (RuntimeException ex) {
            throw new InvalidGenderException("Scelta del gender non valida");
        }
    }

    @Override
    public Anagraphic toEntity(AnagraphicDTO anagraphicDTO) {


        return anagraphicDTO != null ? Anagraphic.builder()
                .id(anagraphicDTO.getId())
                .name(anagraphicDTO.getName())
                .images(anagraphicDTO.getImages())
                .surname(anagraphicDTO.getSurname())
                .birth_date(anagraphicDTO.getBirth_date())
                .gender(stringToGender(anagraphicDTO.getGender()))
                .nationality((anagraphicDTO.getNationality()))
                .province(anagraphicDTO.getProvince())
                .city(anagraphicDTO.getCity())
                .address((anagraphicDTO.getAddress()))
                .user(userConverter.toUserDTOfromLogged(anagraphicDTO.getUser()))
                .build(): null;
    }

    @Override
    public AnagraphicDTO toDTO(Anagraphic anagraphic) {

        return anagraphic != null? AnagraphicDTO.builder()
                .id(anagraphic.getId())
                .name(anagraphic.getName())
                .images(anagraphic.getImages())
                .surname(anagraphic.getSurname())
                .birth_date(anagraphic.getBirth_date())
                .gender(genderToString(anagraphic.getGender()))
                .nationality((anagraphic.getNationality()))
                .province(anagraphic.getProvince())
                .city(anagraphic.getCity())
                .address((anagraphic.getAddress()))
                .user(userConverter.toLoggedDTO(anagraphic.getUser()))
                .build(): null;
    }
}
