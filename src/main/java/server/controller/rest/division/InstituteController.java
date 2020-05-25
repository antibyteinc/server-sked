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

    @GetMapping("/get-institute")
    public Institute getInstitute(@AuthenticationPrincipal User user) {
        Institute cover = instituteRepo.findByUser(user);

        if (cover == null) {
            Institute newCover = new Institute();
            newCover.setUser(user);
            return instituteRepo.save(newCover);
        }

        return instituteRepo.findByUser(user);
    }

    @GetMapping("/get-institute-name-by-id/{id}")
    public String getInstNameById(@PathVariable("id") Institute institute) {
        return institute.getName();
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

    @GetMapping("/get-institute-by-userId/{id}")
    public Institute getInstituteByUserId(@PathVariable("id") User user) {
        return instituteRepo.findByUser(user);
    }

    @PutMapping("/add-institute-name")
    public void addInstituteName(@AuthenticationPrincipal User user, @RequestBody String name) {
        Institute coverFromDb = instituteRepo.findByUser(user);
        coverFromDb.setName(name);
        instituteRepo.save(coverFromDb);
    }

    @PutMapping("/update-institute/{id}")
    public Institute updateInstitute(@PathVariable("id") User user, @RequestBody Institute institute) {
        Institute instituteFromDb = instituteRepo.findByUser(user);
        instituteFromDb.setName(institute.getName());
        instituteFromDb.setDescription(institute.getDescription());
        instituteFromDb.setSite(institute.getSite());
        instituteFromDb.setMail(institute.getMail());
        instituteFromDb.setPhone(institute.getPhone());
        return instituteRepo.save(instituteFromDb);
    }

}
