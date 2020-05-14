package server.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.Department;
import server.domain.Group;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {

    List<Group> findByDepartment(Department department);
}
