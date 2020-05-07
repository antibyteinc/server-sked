package server.controller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import server.domain.security.User;
import server.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Model model, @RequestParam String username, @RequestParam String password) {

        if (username.equals("") || password.equals("")) {
            model.addAttribute("error", "Не всі поля заповнені.");
            System.out.println(model.getAttribute("error"));
            return "registration";
        }

        if (username.contains(" ") || password.contains(" ")) {
            model.addAttribute("error", "Логін повинен бути без пробілів.");
            return "registration";
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (!userService.saveUser(user)){
            model.addAttribute("error", "Цей логін вже зайнятий.");
            return "registration";
        }

        return "redirect:/login";
    }
}
