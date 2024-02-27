package it.contrader.inbook.converter;

import it.contrader.inbook.dto.AnagraphicDTO;
import it.contrader.inbook.model.Anagraphic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnagraphicMapper {
    public Anagraphic toEntity(AnagraphicDTO dto);
    public AnagraphicDTO toDTO(Anagraphic entity);

    public List<Anagraphic> toListEntity(List<AnagraphicDTO> listDto);
    public List<AnagraphicDTO> toListDTO(List<Anagraphic> listEntity);


}
