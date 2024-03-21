package it.contrader.inbook.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    //private String nation;
    private String province;
    private String city;
    private String description;

    @ManyToMany
    @JoinTable(name = "admin_library", joinColumns = @JoinColumn(name = "library_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> admins;

    private String password;

}
