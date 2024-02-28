package it.contrader.inbook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public interface ServiceDTO<DTO> {

    public Page<DTO> getAll(Pageable pageable);

    public DTO read(long id);

    public DTO save(DTO dto);

    public void delete(long id);

}
