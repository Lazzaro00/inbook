package it.contrader.inbook.repository;

import it.contrader.inbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByName(String name);

    List<Book> findByCategory(String category);

    List<Book> findByLibrary_Id(Long library_Id);

    List<Book> findByPriceGreaterThan(double price);

    List<Book> findBySerialcode(String serialcode);

    Long countByCategory(String category);

    Long countByPriceGreaterThan(double price);

    Long countBySerialcode(String serialcode);

    Long countByLibrary_Id(Long library_Id);

    Long countByLibrary_IdAndCategory(Long library_Id, String category);

    Long countByLibrary_IdAndSerialcode(Long library_Id, String serialcode);

    List<Book> findByLibraryNotNull();
}
