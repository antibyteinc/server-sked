package server.controller.rest.division;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.division.Division;
import server.domain.division.Group;
import server.domain.security.User;
import server.repo.division.DivisionRepo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/division")
public class DivisionController {

    private final DivisionRepo divisionRepo;

    public DivisionController(DivisionRepo divisionRepo) {
        this.divisionRepo = divisionRepo;
    }

    @GetMapping
    public List<Division> getAll() {
        return divisionRepo.findAll();
    }

    @GetMapping("/user")
    public List<Division> getByUser(@AuthenticationPrincipal User user) {
        return divisionRepo.findByUser(user);
    }

    @GetMapping("/get-id/{groupId}")
    public Long getIdByGroupName(@PathVariable("groupId") Group group) {
        return group.getDepartment().getCourse().getFaculty().getSemester().getDivision().getId();
    }

    @GetMapping("/get-name/{id}")
    public String getName(@PathVariable("id") Division division) {
        return division.getName();
    }

    @PostMapping("/user")
    public Division add(@AuthenticationPrincipal User user, @RequestBody Division division) {
        division.setUser(user);
        division.setLastModified(new Date().getTime());
        return divisionRepo.save(division);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Division division) {
        divisionRepo.delete(division);
    }
}
