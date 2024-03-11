package it.contrader.inbook.repository;

import it.contrader.inbook.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
