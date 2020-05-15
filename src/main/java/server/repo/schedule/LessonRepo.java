package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Institution;
import server.domain.schedule.Lesson;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {

    List<Lesson> findByInstitution(Institution institution);
}
