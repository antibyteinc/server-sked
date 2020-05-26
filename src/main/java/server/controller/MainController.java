package server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import server.domain.security.Role;
import server.domain.security.User;
import server.repo.security.RoleRepo;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final RoleRepo roleRepo;

    public MainController(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @GetMapping
    public String getPage(Model model, @AuthenticationPrincipal User user) {
        HashMap<String, Object> data = new HashMap<>();

        if (roleRepo.findAll().isEmpty()) {
            Role role = new Role();
            role.setId(1L);
            role.setName("ROLE_USER");
            roleRepo.save(role);
        }

        if (user != null) {
            data.put("id", user.getId());
            data.put("username", user.getUsername());
        }

        model.addAttribute("frontendData", data);
        return "index";
    }
}
