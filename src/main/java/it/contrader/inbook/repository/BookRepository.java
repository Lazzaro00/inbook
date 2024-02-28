package it.contrader.inbook.repository;

import it.contrader.inbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByName(String name);

    List<Book> findByCategory(String category);

    List<Book> findByPriceGreaterThan(double price);

    List<Book> findBySerialcode(String serialcode);

    List<Book> findByLibrary_Id(long library_Id);
}
