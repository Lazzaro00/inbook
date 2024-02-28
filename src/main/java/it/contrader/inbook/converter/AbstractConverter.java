package it.contrader.inbook.converter;

import it.contrader.inbook.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Entity, DTO> implements Converter<Entity, DTO> {

    public List<Entity> toListEntity(List<DTO> dtolist) {
        return dtolist == null ? null : dtolist.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<DTO> toListDTO(List<Entity> entityList) {
        return entityList == null ? null : entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public Page<DTO> toDTOPage(List<DTO> DTOList, int pageNumber, int pageSize) {
        return new PageImpl<>(DTOList, PageRequest.of(pageNumber, pageSize), DTOList.size());
    }

}

