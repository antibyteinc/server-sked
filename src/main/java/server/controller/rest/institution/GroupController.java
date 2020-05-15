package server.controller.rest.institution;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.*;
import server.repo.institution.GroupRepo;

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

    @GetMapping("/get-institution-id/{id}")
    public Long getById(@PathVariable("id") Group group) {
        Department department = group.getDepartment();
        Course course = department.getCourse();
        Faculty faculty = course.getFaculty();
        Semester semester = faculty.getSemester();
        Institution institution = semester.getInstitution();

        return institution.getId();
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
