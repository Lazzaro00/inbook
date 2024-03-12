package it.contrader.inbook.dto;


import it.contrader.inbook.model.Book;
import it.contrader.inbook.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartInsDTO {

    private String userMail;

    private Long bookId;

    private int quantity;

}