package server.controller.rest.division;

import org.springframework.web.bind.annotation.*;
import server.domain.division.Division;
import server.domain.division.Semester;
import server.repo.division.SemesterRepo;

import java.util.List;

@RestController
@RequestMapping("/semester")
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
    public List<Semester> getByInstitution(@PathVariable("id") Division division) {
        return semesterRepo.findByDivision(division);
    }

    @PostMapping("{id}")
    public Semester add(@PathVariable("id") Division division, @RequestBody Semester semester) {
        semester.setDivision(division);
        return semesterRepo.save(semester);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Semester semester) {
        semesterRepo.delete(semester);
    }
}
