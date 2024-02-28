package it.contrader.inbook.controller;

import it.contrader.inbook.service.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class AbstractController<DTO> implements Controller<DTO>{

    @Autowired
    private ServiceDTO<DTO> serviceDTO;

    @GetMapping("/getall")
    @Override
    public ResponseEntity<Page<DTO>> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber){
        return new ResponseEntity<>(serviceDTO.getAll(PageRequest.of(pageSize,pageNumber)),HttpStatus.OK);
    }

    @GetMapping("/read")
    @Override
    public ResponseEntity<DTO> read(@RequestParam("id") long id){
        return new ResponseEntity<>(serviceDTO.read(id),HttpStatus.OK);
    }

    @PostMapping("/insert")
    @Override
    public ResponseEntity<DTO> insert(@RequestBody @Validated DTO dto){
        return new ResponseEntity<>(serviceDTO.save(dto),HttpStatus.OK);
    }

    @PutMapping("/update")
    @Override
    public ResponseEntity<DTO> update(@RequestBody @Validated DTO dto){
        return new ResponseEntity<>(serviceDTO.save(dto),HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Override
    public void delete(@RequestParam("id") long id){
        serviceDTO.delete(id);
    }
}
