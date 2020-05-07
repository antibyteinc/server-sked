package server.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.security.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
