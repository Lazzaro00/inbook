package it.contrader.inbook.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long id;

    private PrivateUserDTO user;

    private BookDTO book;

    private int quantitySelected;

}