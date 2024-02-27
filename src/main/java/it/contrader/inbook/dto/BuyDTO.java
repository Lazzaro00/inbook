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

    private User user;

    private Book book;

    private int quantity;
    private LocalDate date;
}
