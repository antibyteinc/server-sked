package server.repo.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Course;
import server.domain.institution.Faculty;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByFaculty(Faculty faculty);
}
