package it.contrader.inbook.service;

import it.contrader.inbook.converter.AnagraphicConverter;
import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.repository.AnagraphicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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


}
