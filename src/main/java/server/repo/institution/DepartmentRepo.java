package server.repo.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Course;
import server.domain.institution.Department;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    List<Department> findByCourse(Course course);
}
