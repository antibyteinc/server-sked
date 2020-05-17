package server.repo.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import server.domain.institution.Group;
import server.domain.schedule.Schedule;

import java.util.List;

public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

    List<Schedule> findByGroup(Group group);
}
