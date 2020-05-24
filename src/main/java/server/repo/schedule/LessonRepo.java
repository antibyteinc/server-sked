package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.schedule.Lesson;
import server.domain.security.User;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {

    List<Lesson> findByUser(User user);
}
