package it.contrader.inbook.repository;

import it.contrader.inbook.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    public List<Buy> findByUser_IdAndBook_Id(long user_Id, long book_Id);

    public List<Buy> findByUser_Id(long user_Id);

    public List<Buy> findByBook_Id(long book_Id);

    @Query("SELECT b FROM Buy b WHERE b.user IS NOT NULL AND b.book IS NOT NULL")
    List<Buy> findByUserNotNullAndBookNotNull();
}