package server.repo.items;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.items.Lesson;
import server.domain.security.User;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson, Long> {

    List<Lesson> findByUser(User user);
}
