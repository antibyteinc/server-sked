package server.controller.rest.items;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.items.Classroom;
import server.domain.security.User;
import server.repo.items.ClassroomRepo;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomRepo classroomRepo;

    public ClassroomController(ClassroomRepo classroomRepo) {
        this.classroomRepo = classroomRepo;
    }

    @GetMapping()
    public List<Classroom> getByInstitution(@AuthenticationPrincipal User user) {
        return classroomRepo.findByUser(user);
    }

    @PostMapping()
    public Classroom add(@AuthenticationPrincipal User user, @RequestBody Classroom classroom) {
        classroom.setUser(user);
        return classroomRepo.save(classroom);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Classroom classroom) {
        classroomRepo.delete(classroom);
    }
}
