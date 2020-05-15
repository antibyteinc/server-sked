package server.controller.rest.institution;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Institution;
import server.domain.institution.Semester;
import server.repo.institution.SemesterRepo;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    private final SemesterRepo semesterRepo;

    public SemesterController(SemesterRepo semesterRepo) {
        this.semesterRepo = semesterRepo;
    }

    @GetMapping
    public List<Semester> getAll() {
        return semesterRepo.findAll();
    }

    @GetMapping("{id}")
    public List<Semester> getByInstitution(@PathVariable("id") Institution institution) {
        return semesterRepo.findByInstitution(institution);
    }

    @PostMapping("{id}")
    public Semester add(@PathVariable("id") Institution institution, @RequestBody Semester semester) {
        semester.setInstitution(institution);
        return semesterRepo.save(semester);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Semester semester) {
        semesterRepo.delete(semester);
    }
}
