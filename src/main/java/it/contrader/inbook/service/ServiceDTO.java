package it.contrader.inbook.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceDTO<DTO> {

    public Page<DTO> getAll(Pageable pageable);

    public DTO read(Long id);

    public DTO save(DTO dto);

    public void delete(Long id);

    public List<DTO> getAll();

}
