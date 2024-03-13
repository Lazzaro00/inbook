package it.contrader.inbook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibraryDTO {

    private Long id;

    private String name;
    private String address;
    private String nation;
    private String province;
    private String city;
    private String description;

    private Set<PrivateUserDTO> admins;

}
