package it.contrader.inbook.converter;

import it.contrader.inbook.dto.LibraryDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.model.Library;
import it.contrader.inbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LibraryConverter extends AbstractConverter<Library, LibraryDTO> {

    @Autowired
    UserConverter userConverter;

    private Set<UserDTO> userSetEntoDTO(Set<User> users) {
        if (users == null)
            return null;

        return users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toSet());
    }

    private Set<User> userDTOtoEn(Set<UserDTO> userDtos) {
        if (userDtos == null)
            return null;

        return userDtos.stream()
                .map(userConverter::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Library toEntity(LibraryDTO libraryDTO) {
        return libraryDTO != null ? Library.builder()
                .id(libraryDTO.getId())
                .name(libraryDTO.getName())
                .address(libraryDTO.getAddress())
                .city(libraryDTO.getCity())
                .description(libraryDTO.getDescription())
                .nation(libraryDTO.getNation())
                .province(libraryDTO.getProvince())
                .admins(userDTOtoEn(libraryDTO.getAdmins()))
                .build():null;

    }

    @Override
    public LibraryDTO toDTO(Library library) {


        return library!=null? LibraryDTO.builder()
                .id(library.getId())
                .name(library.getName())
                .address(library.getAddress())
                .city(library.getCity())
                .nation(library.getNation())
                .province(library.getProvince())
                .description(library.getDescription())
                .admins(userSetEntoDTO(library.getAdmins()))
                .build():null;
    }


}
