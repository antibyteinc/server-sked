package server.controller.rest.schedule;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.schedule.Lesson;
import server.domain.security.User;
import server.repo.schedule.LessonRepo;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepo lessonRepo;

    public LessonController(LessonRepo lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @GetMapping()
    public List<Lesson> getByInstitution(@AuthenticationPrincipal User user) {
        return lessonRepo.findByUser(user);
    }

    @PostMapping()
    public Lesson add(@AuthenticationPrincipal User user, @RequestBody Lesson lesson) {
        lesson.setUser(user);
        return lessonRepo.save(lesson);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Lesson lesson) {
        lessonRepo.delete(lesson);
    }
}
