package it.contrader.inbook.repository;

import it.contrader.inbook.model.Anagraphic;
import it.contrader.inbook.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnagraphicRepository extends JpaRepository<Anagraphic,Long> {
    public Optional<Anagraphic> findByUser_Id(long userId);

    @Query("SELECT COUNT(a) FROM Anagraphic a WHERE a.birth_date >= :birthDate")
    Long countByBirthDateStartingFrom(@Param("birthDate") LocalDate birthDate);

    Long countByGender(Anagraphic.Gender gender);

    Long countByNationality(String nationality);

    Long countByProvince(String province);

    Long countByCity(String city);

    Long countByAddress(String address);

    List<Anagraphic> findByUserNotNull();

    List<Anagraphic> findByUser_Roles(Optional<Role> role);
}
