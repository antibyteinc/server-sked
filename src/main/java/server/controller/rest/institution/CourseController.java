package server.controller.rest.institution;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Course;
import server.domain.institution.Faculty;
import server.repo.institution.CourseRepo;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepo courseRepo;

    public CourseController(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @GetMapping("{id}")
    public List<Course> getByFaculty(@PathVariable("id")Faculty faculty) {
        return courseRepo.findByFaculty(faculty);
    }

    @PostMapping("{id}")
    public Course add(@PathVariable("id") Faculty faculty, @RequestBody Course course) {
        course.setFaculty(faculty);
        return courseRepo.save(course);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Course course) {
        courseRepo.delete(course);
    }
}
