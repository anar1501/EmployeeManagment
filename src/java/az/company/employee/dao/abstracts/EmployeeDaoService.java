package az.company.employee.dao.abstracts;

import az.company.employee.model.concrets.Employee;
import java.util.List;

public interface EmployeeDaoService {
    
    List<Employee>findAll();

    void save(Employee employee);

    public Employee findById(int id);

    public void updateById(Employee employee);

    public void deleteById(Employee employee);
    
}
