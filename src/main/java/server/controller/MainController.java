package server.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import server.domain.security.User;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String getPage(Model model, @AuthenticationPrincipal User user) {
        HashMap<String, Object> data = new HashMap<>();

        if (user != null) {
            data.put("username", user.getUsername());
        }

        model.addAttribute("frontendData", data);
        return "index";
    }
}
