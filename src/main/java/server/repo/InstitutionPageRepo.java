package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.InstitutionPage;
import server.domain.security.User;

public interface InstitutionPageRepo extends JpaRepository<InstitutionPage, Long> {

    InstitutionPage findByUser(User user);
}
