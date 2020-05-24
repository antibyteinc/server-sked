package server.controller.rest;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import server.domain.InstitutionPage;
import server.domain.security.User;
import server.repo.InstitutionPageRepo;

@RestController
@RequestMapping("/institution-page")
public class InstitutionPageController {

    private final InstitutionPageRepo institutionPageRepo;

    public InstitutionPageController(InstitutionPageRepo institutionPageRepo) {
        this.institutionPageRepo = institutionPageRepo;
    }

//    @GetMapping("/view")
//    public InstitutionPage getInstitutionPageForView(@RequestBody Long id) {
//        return institutionPageRepo.getOne(id);
//    }

    @GetMapping("/edit")
    public InstitutionPage getInstitutionPageForEdit(@AuthenticationPrincipal User user) {
        InstitutionPage page = institutionPageRepo.findByUser(user);

        if (page == null) {
            InstitutionPage institutionPage = new InstitutionPage();
            institutionPage.setUser(user);
            return institutionPageRepo.save(institutionPage);
        }

        return institutionPageRepo.findByUser(user);
    }

    @PostMapping("/edit/{id}")
    public InstitutionPage add(@PathVariable("id") User user, @RequestBody InstitutionPage institutionPage) {
        institutionPage.setUser(user);
        return institutionPageRepo.save(institutionPage);
    }

    @PutMapping("/edit/{id}")
    public InstitutionPage update(@AuthenticationPrincipal User user, @PathVariable("id") InstitutionPage institutionPageFromDb, @RequestBody InstitutionPage institutionPage) {
        BeanUtils.copyProperties(institutionPage, institutionPageFromDb, "id");
        institutionPage.setUser(user);
        return institutionPageRepo.save(institutionPage);
    }

    @DeleteMapping("/edit/{id}")
    public void delete(@PathVariable("id") InstitutionPage institutionPage) {
        institutionPageRepo.delete(institutionPage);
    }

}
