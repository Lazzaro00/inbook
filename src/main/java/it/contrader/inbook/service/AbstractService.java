package it.contrader.inbook.service;

import it.contrader.inbook.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<Entity,DTO> implements ServiceDTO<DTO> {

    @Autowired
    private Converter<Entity,DTO> converter;

    @Autowired
    private JpaRepository<Entity, Long> repository;


    @Autowired
    public List<DTO> getAll(){
        return converter.toListDTO(repository.findAll());
    }

    @Autowired
    public DTO read(long id){
        return converter.toDTO(repository.findById(id).get()); //todo inserire eccezione
    }

    @Autowired
    public DTO save(DTO dto){
        return converter.toDTO(repository.save(converter.toEntity(dto)));
    }


    @Autowired
    public void delete(long id){
        repository.deleteById(id);
    }


}
