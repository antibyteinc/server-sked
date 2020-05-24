package server.controller.rest.division;

import org.springframework.web.bind.annotation.*;
import server.domain.division.Faculty;
import server.domain.division.Semester;
import server.repo.division.FacultyRepo;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyRepo facultyRepo;

    public FacultyController(FacultyRepo facultyRepo) {
        this.facultyRepo = facultyRepo;
    }

    @GetMapping("{id}")
    public List<Faculty> getBySemester(@PathVariable("id") Semester semester) {
        return facultyRepo.findBySemester(semester);
    }

    @PostMapping("{id}")
    public Faculty add(@PathVariable("id") Semester semester, @RequestBody Faculty faculty) {
        faculty.setSemester(semester);
        return facultyRepo.save(faculty);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Faculty faculty) {
        facultyRepo.delete(faculty);
    }
}
