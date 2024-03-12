package it.contrader.inbook.repository;

import it.contrader.inbook.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    public List<Cart> findByUser_Id(Long user_Id);
}
