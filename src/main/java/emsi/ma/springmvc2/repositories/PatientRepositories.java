package emsi.ma.springmvc2.repositories;

import emsi.ma.springmvc2.entites.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositories extends JpaRepository<Patient,Long> {
    Page<Patient> findByNomContainsAndPrenomContainsAndScoreGreaterThanEqual(String kw,String pr,int sc, Pageable pageable);
    Patient findPatientById(long id);
}
