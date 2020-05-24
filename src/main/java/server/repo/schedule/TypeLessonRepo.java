package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.schedule.TypeLesson;
import server.domain.security.User;

import java.util.List;

public interface TypeLessonRepo extends JpaRepository<TypeLesson, Long> {

    List<TypeLesson> findByUser(User user);
}
