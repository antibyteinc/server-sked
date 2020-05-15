package server.repo.institution;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Department;
import server.domain.institution.Group;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {

    List<Group> findByDepartment(Department department);
}
