package it.contrader.inbook.repository;

import it.contrader.inbook.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library,Long> {
}
