package server.controller.rest.division;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.division.Institute;
import server.domain.security.User;
import server.repo.division.InstituteRepo;

import java.util.List;

@RestController
@RequestMapping("/institute")
public class InstituteController {

    private final InstituteRepo instituteRepo;

    public InstituteController(InstituteRepo instituteRepo) {
        this.instituteRepo = instituteRepo;
    }

    @GetMapping("/get-institutions")
    public List<Institute> getAll() {
        return instituteRepo.findAll();
    }

    @GetMapping("/get-institute-name")
    public String getInstitutionName(@AuthenticationPrincipal User user) {
        Institute cover = instituteRepo.findByUser(user);

        if (cover == null) {
            Institute newCover = new Institute();
            newCover.setUser(user);
            return instituteRepo.save(newCover).getName();
        }

        return instituteRepo.findByUser(user).getName();
    }

    @PutMapping("/add-institute-name")
    public void addInstitutionName(@AuthenticationPrincipal User user, @RequestBody String name) {
        Institute coverFromDb = instituteRepo.findByUser(user);
        coverFromDb.setName(name);
        instituteRepo.save(coverFromDb);
    }

}
