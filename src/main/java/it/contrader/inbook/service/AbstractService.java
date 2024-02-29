package it.contrader.inbook.service;

import it.contrader.inbook.converter.Converter;
import it.contrader.inbook.exception.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class AbstractService<Entity,DTO> implements ServiceDTO<DTO> {

    @Autowired
    private Converter<Entity,DTO> converter;

    @Autowired
    private JpaRepository<Entity, Long> repository;


    @Override
    public Page<DTO> getAll(Pageable pageable){
        return converter.toDTOPage(converter.toListDTO(repository.findAll()), pageable.getPageNumber(),  pageable.getPageSize());
    }

    @Override
    public DTO read(Long id){
        return converter.toDTO(repository.findById(id).orElseThrow(() -> new UserNotExistException("User is not exist!")));
    }

    @Override
    public DTO save(DTO dto){
        return converter.toDTO(repository.save(converter.toEntity(dto)));
    }


    @Override
    public void delete(Long id){
        repository.deleteById(id);
    }


}
