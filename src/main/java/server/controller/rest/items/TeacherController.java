package server.controller.rest.items;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.items.Teacher;
import server.domain.security.User;
import server.repo.items.TeacherRepo;

import java.util.List;

@RestController
@RequestMapping("/teacher")
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
