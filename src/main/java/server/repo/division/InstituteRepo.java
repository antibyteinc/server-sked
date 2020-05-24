package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Institute;
import server.domain.security.User;

public interface InstituteRepo extends JpaRepository<Institute, Long> {

    Institute findByUser(User user);
}
