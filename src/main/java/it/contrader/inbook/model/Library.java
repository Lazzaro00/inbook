package it.contrader.inbook.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String address;
    private String nation;
    private String province;
    private String city;
    private String description;

    @ManyToMany
    @JoinTable(name = "user_library", joinColumns = @JoinColumn(name = "library_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

}
