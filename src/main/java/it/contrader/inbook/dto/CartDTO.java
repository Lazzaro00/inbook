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
public class CartDTO {

    private Long id;

    private UserDTO user;

    private BookDTO book;

    private int quantity;

}