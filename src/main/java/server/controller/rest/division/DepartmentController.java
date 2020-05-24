package server.controller.rest.division;

import org.springframework.web.bind.annotation.*;
import server.domain.division.Course;
import server.domain.division.Department;
import server.repo.division.DepartmentRepo;

import java.util.List;

@RestController
@RequestMapping("/department")
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
