package server.controller.rest.schedule;

import org.springframework.web.bind.annotation.*;
import server.domain.schedule.Classroom;
import server.domain.institution.Institution;
import server.repo.schedule.ClassroomRepo;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomRepo classroomRepo;

    public ClassroomController(ClassroomRepo classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    @GetMapping("{id}")
    public List<Classroom> getByInstitution(@PathVariable("id") Institution institution) {
        return classroomRepo.findByInstitution(institution);
    }

    @PostMapping("{id}")
    public Classroom add(@PathVariable("id") Institution institution, @RequestBody Classroom classroom) {
        classroom.setInstitution(institution);
        return classroomRepo.save(classroom);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Classroom classroom) {
        classroomRepo.delete(classroom);
    }
}
