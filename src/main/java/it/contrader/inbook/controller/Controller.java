package it.contrader.inbook.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;


public interface Controller<DTO> {
    public ResponseEntity<Page<DTO>> getAll(int pageSize, int pageNumber);

    public ResponseEntity<DTO> read(long id);

    public ResponseEntity<DTO> insert(DTO dto);

    public ResponseEntity<DTO> update(DTO dto);

    public void delete(long id);
}
