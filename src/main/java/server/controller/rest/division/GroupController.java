package server.controller.rest.division;

import org.springframework.web.bind.annotation.*;
import server.domain.division.*;
import server.repo.division.GroupRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupRepo groupRepo;

    public GroupController(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }

    @GetMapping("{id}")
    public List<Group> getByDepartment(@PathVariable("id") Department department) {
        return groupRepo.findByDepartment(department);
    }

    @GetMapping("/get-name/{id}")
    public String getNameById(@PathVariable("id") Group group) {
        return group.getName();
    }

    @GetMapping("/get-semesterRange/{id}")
    public Map<String, Long> getSemesterRangeById(@PathVariable("id") Group group) {
        Semester semester = group.getDepartment().getCourse().getFaculty().getSemester();
        Long start = semester.getStart();
        Long finish = semester.getFinish();

        Map<String, Long> semesterRange = new HashMap<>();
        semesterRange.put("start", start);
        semesterRange.put("finish", finish);

        return semesterRange;
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

    @GetMapping("/get-version/{id}")
    public Long getVersion(@PathVariable("id") Group group) {
        return group.getVersion();
    }

    @PutMapping("/update-version/{id}")
    public void updateVersion(@PathVariable("id") Group group, @RequestBody Integer num) {
        group.setVersion(group.getVersion() + num);
        groupRepo.save(group);
    }
}
