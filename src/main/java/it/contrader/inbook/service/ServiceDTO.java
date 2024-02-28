package it.contrader.inbook.service;

import java.util.List;

public interface ServiceDTO<DTO> {

    public List<DTO> getAll();

    public DTO read(long id);

    public DTO save(DTO dto);

    public void delete(long id);

}
