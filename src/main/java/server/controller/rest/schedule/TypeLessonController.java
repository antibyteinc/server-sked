package server.controller.rest.schedule;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.schedule.TypeLesson;
import server.repo.schedule.TypeLessonRepo;

import java.util.List;

@RestController
@RequestMapping("/type-lessons")
public class TypeLessonController {

    private final TypeLessonRepo typeLessonRepo;

    public TypeLessonController(TypeLessonRepo typeLessonRepo) {
        this.typeLessonRepo = typeLessonRepo;
    }

    @GetMapping("{id}")
    public List<TypeLesson> getByInstitution(@PathVariable("id") Institution institution) {
        return typeLessonRepo.findByInstitution(institution);
    }

    @PostMapping("{id}")
    public TypeLesson add(@PathVariable("id") Institution institution, @RequestBody TypeLesson typeLesson) {
        typeLesson.setInstitution(institution);
        return typeLessonRepo.save(typeLesson);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") TypeLesson typeLesson) {
        typeLessonRepo.delete(typeLesson);
    }
}
