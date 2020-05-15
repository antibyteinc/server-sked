package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Institution;
import server.domain.schedule.TypeLesson;

import java.util.List;

public interface TypeLessonRepo extends JpaRepository<TypeLesson, Long> {

    List<TypeLesson> findByInstitution(Institution institution);
}
