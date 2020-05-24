package server.controller.rest.schedule;

import org.springframework.web.bind.annotation.*;
import server.domain.division.Group;
import server.domain.schedule.Schedule;
import server.repo.schedule.ScheduleRepo;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleRepo scheduleRepo;

    public ScheduleController(ScheduleRepo scheduleRepo) {
        this.scheduleRepo = scheduleRepo;
    }

    @GetMapping("{id}")
    public List<Schedule> getByGroup(@PathVariable("id") Group group) {
        return scheduleRepo.findByGroup(group);
    }

    @PostMapping("{id}")
    public Schedule add(@PathVariable("id") Group group, @RequestBody Schedule schedule) {
        schedule.setGroup(group);
        return scheduleRepo.save(schedule);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Schedule schedule) {
        scheduleRepo.delete(schedule);
    }
}
