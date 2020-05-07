package server.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/institutions")
public class InstitutionController {

    @GetMapping
    public String getAll() {
        return "institutions";
    }
}
