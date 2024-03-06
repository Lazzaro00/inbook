package it.contrader.inbook.dto;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnagraphicDTO {


    private Long id;

    @Lob
    private byte[] images;

    private String name;
    private String surname;
    private LocalDate birth_date;
    private String gender;
    private String nationality;
    private String province;
    private String city;
    private String address;

    private LoggedDTO user;
}