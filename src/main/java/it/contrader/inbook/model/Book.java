package it.contrader.inbook.model;

import javax.persistence.*;

import java.util.List;

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
