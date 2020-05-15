package server.repo.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Institution;
import server.domain.institution.Semester;

import java.util.List;

public interface SemesterRepo extends JpaRepository<Semester, Long> {

    List<Semester> findByInstitution(Institution institution);
}
