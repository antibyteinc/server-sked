package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Course;
import server.domain.Department;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

    List<Department> findByCourse(Course course);
}
