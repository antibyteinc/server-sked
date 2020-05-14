package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Institution;
import server.domain.Semester;

import java.util.List;

public interface SemesterRepo extends JpaRepository<Semester, Long> {

    List<Semester> findByInstitution(Institution institution);
}
