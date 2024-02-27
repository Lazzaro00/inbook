package it.contrader.inbook.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Lob
    private List<byte[]> images;
    private String name;
    private String category;
    private double price;
    @Column(length = 50)
    private String serialcode;
    private int quantity;
    private String description;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

}
