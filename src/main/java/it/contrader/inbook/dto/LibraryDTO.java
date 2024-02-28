package it.contrader.inbook.dto;

import it.contrader.inbook.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {

    private long id;

    private String name;
    private String address;
    private String nation;
    private String province;
    private String city;
    private String description;

    private Set<UserDTO> admins;

}
