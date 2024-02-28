package it.contrader.inbook.repository;

import it.contrader.inbook.model.Buy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy,Long> {
    public List<Buy> findByUser_IdAndBook_Id(long user_Id, long book_Id);
}
