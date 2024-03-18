package it.contrader.inbook.repository;

import it.contrader.inbook.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    public List<Cart> findByUser_Id(Long user_Id);

    public List<Cart> findByUser_Email(String user_Email);

    public List<Cart> findByBook_Id(Long book_Id);
}
