package server.controller.rest;

import org.springframework.web.bind.annotation.*;
import server.domain.Department;
import server.domain.Group;
import server.repo.GroupRepo;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupRepo groupRepo;

    public GroupController(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @GetMapping("{id}")
    public List<Group> getByDepartment(@PathVariable("id") Department department) {
        return groupRepo.findByDepartment(department);
    }

    @PostMapping("{id}")
    public Group add(@PathVariable("id") Department department, @RequestBody Group group) {
        group.setDepartment(department);
        return groupRepo.save(group);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Group group) {
        groupRepo.delete(group);
    }
}
