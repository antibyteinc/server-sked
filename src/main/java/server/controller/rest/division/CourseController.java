package server.controller.rest.division;

import org.springframework.web.bind.annotation.*;
import server.domain.division.Course;
import server.domain.division.Faculty;
import server.repo.division.CourseRepo;

import java.util.List;

@RestController
@RequestMapping("/course")
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
