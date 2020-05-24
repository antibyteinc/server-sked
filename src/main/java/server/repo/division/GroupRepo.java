package server.repo.division;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.division.Department;
import server.domain.division.Group;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {

    List<Group> findByDepartment(Department department);
}
