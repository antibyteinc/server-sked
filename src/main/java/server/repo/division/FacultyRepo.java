package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Faculty;
import server.domain.division.Semester;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    List<Faculty> findBySemester(Semester semester);
}
