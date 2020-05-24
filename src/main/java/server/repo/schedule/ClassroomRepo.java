package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.schedule.Classroom;
import server.domain.security.User;

import java.util.List;

public interface ClassroomRepo extends JpaRepository<Classroom, Long> {

    List<Classroom> findByUser(User user);
}
