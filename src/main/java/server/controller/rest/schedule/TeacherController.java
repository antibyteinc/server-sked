package server.controller.rest.schedule;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.schedule.Teacher;
import server.repo.schedule.TeacherRepo;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepo teacherRepo;

    public TeacherController(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @GetMapping("{id}")
    public List<Teacher> getByInstitution(@PathVariable("id")Institution institution) {
        return teacherRepo.findByInstitution(institution);
    }

    @PostMapping("{id}")
    public Teacher add(@PathVariable("id") Institution institution, @RequestBody Teacher teacher) {
        teacher.setInstitution(institution);
        return teacherRepo.save(teacher);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Teacher teacher) {
        teacherRepo.delete(teacher);
    }
}
