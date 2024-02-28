package it.contrader.inbook.service;

import it.contrader.inbook.converter.BuyConverter;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.model.Buy;
import it.contrader.inbook.repository.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BuyService extends AbstractService<Buy, BuyDTO>{

    @Autowired
    BuyConverter converter;

    @Autowired
    BuyRepository repository;

    public List<BuyDTO> getByUser_IdAndBook_Id(long user_Id, long book_Id){
        return converter.toListDTO(repository.findByUser_IdAndBook_Id(user_Id, book_Id));
    }
}
