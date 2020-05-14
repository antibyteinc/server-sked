package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Institution;
import server.domain.security.User;

import java.util.List;

public interface InstitutionRepo extends JpaRepository<Institution, Long> {

    List<Institution> findByUser(User user);
}
