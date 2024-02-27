package it.contrader.inbook.converter;

import java.util.List;

public interface Converter<Entity,DTO> {

    public Entity toEntity(DTO dto);

    public DTO toDTO(Entity entity);

    public List<Entity> toListEntity(List<DTO> listDto);
    public List<DTO> toListDTO(List<Entity> listEntity);
}
