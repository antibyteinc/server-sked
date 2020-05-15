package server.controller.rest.schedule;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.schedule.Lesson;
import server.repo.schedule.LessonRepo;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepo lessonRepo;

    public LessonController(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @GetMapping("{id}")
    public List<Lesson> getByInstitution(@PathVariable("id") Institution institution) {
        return lessonRepo.findByInstitution(institution);
    }

    @PostMapping("{id}")
    public Lesson add(@PathVariable("id") Institution institution, @RequestBody Lesson lesson) {
        lesson.setInstitution(institution);
        return lessonRepo.save(lesson);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Lesson lesson) {
        lessonRepo.delete(lesson);
    }
}
