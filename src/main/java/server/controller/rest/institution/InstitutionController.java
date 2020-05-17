package server.controller.rest.institution;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.institution.Group;
import server.domain.institution.Institution;
import server.domain.security.User;
import server.repo.institution.InstitutionRepo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/institutions")
public class InstitutionController {

    private final InstitutionRepo institutionRepo;

    public InstitutionController(InstitutionRepo institutionRepo) {
        this.institutionRepo = institutionRepo;
    }

    @GetMapping
    public List<Institution> getAll() {
        return institutionRepo.findAll();
    }

    @GetMapping("/user")
    public List<Institution> getByUser(@AuthenticationPrincipal User user) {
        return institutionRepo.findByUser(user);
    }

    @GetMapping("/get-id/{groupId}")
    public Long getIdByGroupName(@PathVariable("groupId") Group group) {
        return group.getDepartment().getCourse().getFaculty().getSemester().getInstitution().getId();
    }

    @GetMapping("/get-name/{id}")
    public String getName(@PathVariable("id") Institution institution) {
        return institution.getName();
    }

    @PostMapping("/user")
    public Institution add(@AuthenticationPrincipal User user, @RequestBody Institution institution) {
        institution.setUser(user);
        institution.setLastModified(new Date().getTime());
        return institutionRepo.save(institution);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable("id") Institution institution) {
        institutionRepo.delete(institution);
    }
}
