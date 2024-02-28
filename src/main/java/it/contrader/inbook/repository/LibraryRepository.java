package it.contrader.inbook.repository;

import it.contrader.inbook.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library,Long> {

    public List<Library> findByAdmins_Id(long userId);

}
