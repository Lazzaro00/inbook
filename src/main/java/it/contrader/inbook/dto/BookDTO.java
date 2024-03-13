package it.contrader.inbook.dto;


import it.contrader.inbook.model.Library;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;

    @Lob
    private byte[] images;
    private String name;
    private String category;
    private double price;
    private String serialcode;
    private int quantity;
    private String description;

    private LibraryDTO library;

}

