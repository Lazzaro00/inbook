package it.contrader.inbook.dto;


import it.contrader.inbook.model.Library;

import javax.persistence.*;

import java.util.List;

public class BookDTO {
    private long id;

    @Lob
    private List<byte[]> images;
    private String name;
    private String category;
    private double price;
    private String serialcode;
    private int quantity;
    private String description;

    private LibraryDTO library;

}

