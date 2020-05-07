package server.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.security.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
