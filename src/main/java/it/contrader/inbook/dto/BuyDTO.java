package it.contrader.inbook.dto;


import it.contrader.inbook.model.Book;
import it.contrader.inbook.model.User;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {

    private long id;

    private UserDTO user;

    private BookDTO book;

    private int quantity;
    private LocalDate date;
}
