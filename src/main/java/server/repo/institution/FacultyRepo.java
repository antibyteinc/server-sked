package server.repo.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Faculty;
import server.domain.institution.Semester;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    List<Faculty> findBySemester(Semester semester);
}
