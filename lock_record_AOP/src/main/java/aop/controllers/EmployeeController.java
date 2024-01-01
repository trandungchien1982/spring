package aop.controllers;

import aop.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import aop.services.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "/add-employee")
    public Employee addEmployee(@RequestParam("name") String name, @RequestParam("empId") String empId) {
        return employeeService.createEmployee(name, empId);
    }

    @GetMapping(value = "/update-employee")
    public void updateEmployee() {
        employeeService.updateEmployee("curEmpId", new Employee(), 123);
    }

    @GetMapping(value = "/remove-employee")
    public String removeEmployee( @RequestParam("empId") String empId) {
        employeeService.deleteEmployee(empId);
        return "Employee removed";
    }

    @GetMapping(value = "/exception-employee")
    public String exceptionCase( @RequestParam("empId") String empId) throws Exception {
        try {
            employeeService.executeWithException(empId);
        } catch (Exception ignore) {}

        return "Execute with Exception ...";
    }
}
