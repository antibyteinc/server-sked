package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Course;
import server.domain.Faculty;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByFaculty(Faculty faculty);
}
