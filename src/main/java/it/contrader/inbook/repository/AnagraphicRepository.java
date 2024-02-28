package it.contrader.inbook.repository;

import it.contrader.inbook.model.Anagraphic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnagraphicRepository extends JpaRepository<Anagraphic,Long> {
    public Optional<Anagraphic> findByUser_Id(long userId);
}
