package it.contrader.inbook.converter;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface Converter<Entity,DTO> {

    public Entity toEntity(DTO dto);

    public DTO toDTO(Entity entity);

    public List<Entity> toListEntity(List<DTO> listDto);

    public List<DTO> toListDTO(List<Entity> listEntity);

    public Page<DTO> toDTOPage(List<DTO> DTOList, int pageNumber, int pageSize);
}
