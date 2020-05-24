package server.repo.items;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.items.Classroom;
import server.domain.security.User;

import java.util.List;

public interface ClassroomRepo extends JpaRepository<Classroom, Long> {

    List<Classroom> findByUser(User user);
}
