package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Institution;
import server.domain.schedule.Teacher;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    List<Teacher> findByInstitution(Institution institution);
}
