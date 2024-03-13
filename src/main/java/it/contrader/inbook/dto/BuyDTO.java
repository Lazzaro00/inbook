package it.contrader.inbook.dto;


import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuyDTO {

    private Long id;

    private PrivateUserDTO user;

    private BookDTO book;

    private int quantity;
    private LocalDate date;
}
