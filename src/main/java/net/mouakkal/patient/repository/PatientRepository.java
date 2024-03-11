package net.mouakkal.patient.repository;

import net.mouakkal.patient.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNomContains(String keyword, Pageable pageable);
    Patient findByNom(String nom);
    @Query("select p from Patient p where p.nom like :x")
    Patient search(@Param("x") String keyword, Pageable pageable);
}
