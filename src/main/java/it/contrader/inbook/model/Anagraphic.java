package it.contrader.inbook.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Anagraphic {
    public enum Gender{
        Male,
        Female
    };


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private byte[] images;

    private String name;
    private String surname;
    private LocalDate birth_date;
    private Gender gender;
    private String nationality;
    private String province;
    private String city;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}