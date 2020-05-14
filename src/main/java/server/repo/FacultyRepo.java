package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Faculty;
import server.domain.Semester;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

    List<Faculty> findBySemester(Semester semester);
}
