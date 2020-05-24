package server.controller.rest.schedule;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.schedule.Teacher;
import server.domain.security.User;
import server.repo.schedule.TeacherRepo;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepo teacherRepo;

    public TeacherController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @GetMapping()
    public List<Teacher> getForUser(@AuthenticationPrincipal User user) {
        return teacherRepo.findByUser(user);
    }

    @PostMapping()
    public Teacher add(@AuthenticationPrincipal User user, @RequestBody Teacher teacher) {
        teacher.setUser(user);
        return teacherRepo.save(teacher);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Teacher teacher) {
        teacherRepo.delete(teacher);
    }
}
