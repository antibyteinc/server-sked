package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Course;
import server.domain.division.Faculty;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByFaculty(Faculty faculty);
}
