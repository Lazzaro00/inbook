package it.contrader.inbook.model;



import javax.persistence.*;
import java.time.LocalDate;

public class Buy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private int quantity;
    private LocalDate date;

}
