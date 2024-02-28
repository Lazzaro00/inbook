package it.contrader.inbook.service;

import it.contrader.inbook.converter.LibraryConverter;
import it.contrader.inbook.dto.LibraryDTO;
import it.contrader.inbook.model.Library;

@Service
public class LibraryService extends AbstractService<Library, LibraryDTO>{

    @Autowired
    LibraryConverter converter;

    @Autowired
    LibraryRepository repository;

    public List<LibraryDTO> getByAdmin_Id(long adminId){
        return converter.toListDTO(repository.findByAdmins_Id(adminId));
    }
}
