package it.contrader.inbook.service;

import it.contrader.inbook.converter.Converter;
import it.contrader.inbook.exception.NotExistException;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


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
        return converter.toDTO(repository.findById(id).orElseThrow(() -> new NotExistException("User is not exist!")));
    }

    @Override
    public DTO save(DTO dto){
        try {
            return converter.toDTO(repository.save(converter.toEntity(dto)));
        }
        catch(RuntimeException ex){
            throw new RuntimeException("Error in ssociate element");
        }
    }


    @Override
    public void delete(Long id){
        try {
            repository.deleteById(id);
        }
        catch (Exception ex){
            throw  new NotExistException("Is not exist anymore");
        }
    }


}
