package aop.services;

import aop.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    Logger log = LoggerFactory.getLogger(getClass());

    public void updateEmployee(String empId, Employee emp, int newAge) {
        emp.setEmpId(empId);
        emp.setName("NameWithAge: " + newAge);
        try {
            Thread.sleep(100);
        } catch (Exception ignored) {}

        log.info(" .. Finish process of updateEmployee(...)");
    }

    public Employee createEmployee(String name, String empId) {
        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmpId(empId);
        try {
            Thread.sleep(200);
        } catch (Exception ignored) {}

        log.info(" .. Finish process of createEmployee(...)");
        return emp;
    }

    public void executeWithException(String empId) throws Exception {
        try {
            Thread.sleep(5);
        } catch (Exception ignored) {}
        throw new NullPointerException("Dummy NPE :)");
    }

    public void deleteEmployee(String empId) {
        try {
            Thread.sleep(10);
        } catch (Exception ignored) {}

        log.info(" .. Finish process of deleteEmployee(...)");
    }
}
