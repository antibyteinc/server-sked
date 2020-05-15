package server.controller.rest.institution;

import org.springframework.web.bind.annotation.*;
import server.domain.institution.Course;
import server.domain.institution.Department;
import server.repo.institution.DepartmentRepo;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentRepo departmentRepo;

    public DepartmentController(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @GetMapping("{id}")
    public List<Department> getByCourse(@PathVariable("id") Course course) {
        return departmentRepo.findByCourse(course);
    }

    @PostMapping("{id}")
    public Department add(@PathVariable("id") Course course, @RequestBody Department department) {
        department.setCourse(course);
        return departmentRepo.save(department);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Department department) {
        departmentRepo.delete(department);
    }
}
