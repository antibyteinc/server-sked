package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Course;
import server.domain.division.Department;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    List<Department> findByCourse(Course course);
}
