package server.repo.items;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.items.TypeLesson;
import server.domain.security.User;

import java.util.List;

public interface TypeLessonRepo extends JpaRepository<TypeLesson, Long> {

    List<TypeLesson> findByUser(User user);
}
