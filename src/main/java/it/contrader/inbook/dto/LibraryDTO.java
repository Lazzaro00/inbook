package it.contrader.inbook.dto;

import it.contrader.inbook.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


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

    private UserDTO user;

}
