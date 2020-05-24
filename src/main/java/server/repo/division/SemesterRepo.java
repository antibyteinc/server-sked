package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Division;
import server.domain.division.Semester;

import java.util.List;

public interface SemesterRepo extends JpaRepository<Semester, Long> {

    List<Semester> findByDivision(Division division);
}
