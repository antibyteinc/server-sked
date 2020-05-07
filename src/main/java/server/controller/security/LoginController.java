package server.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String getLogin(
            @RequestParam(value = "error", required = false) String error,
            Model model
    ) {
        model.addAttribute("error", error);

        return "login";
    }
}
