package it.contrader.inbook.service;

import it.contrader.inbook.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractService<Entity,DTO> implements ServiceDTO<DTO> {

    @Autowired
    private Converter<Entity,DTO> converter;

    @Autowired
    private JpaRepository<Entity, Long> repository;


    @Autowired
    public Page<DTO> getAll(Pageable pageable){
        return converter.toDTOPage(converter.toListDTO(repository.findAll()), pageable.getPageNumber(),  pageable.getPageSize());
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
