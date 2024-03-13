package it.contrader.inbook.repository;

import it.contrader.inbook.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {

    public List<Library> findByAdmins_Id(long userId);

    public List<Library> findByAdmins_Email(String email);

    public List<Library> findByAdminsNotNull();

    public List<Library> findByCity(String city);
}
