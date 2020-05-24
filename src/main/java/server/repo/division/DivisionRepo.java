package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Division;
import server.domain.security.User;

import java.util.List;

public interface DivisionRepo extends JpaRepository<Division, Long> {

    List<Division> findByUser(User user);
}
