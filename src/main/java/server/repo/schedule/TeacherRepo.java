package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.schedule.Teacher;
import server.domain.security.User;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {

    List<Teacher> findByUser(User user);
}
