package az.company.employee.dao.concrets;

import az.company.employee.connection.concrets.DbConnection;
import az.company.employee.dao.abstracts.EmployeeDaoService;
import az.company.employee.model.concrets.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoManager implements EmployeeDaoService {

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setSurname(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setSalary(resultSet.getDouble(5));
                employee.setStatus(resultSet.getInt(6));
                employees.add(employee);
            }
        } catch (Exception e) {
            System.out.println("findAll error: " + e);
        }
        return employees;
    }

    @Override
    public void save(Employee employee) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into employee(name,surname,age,salary) values(?,?,?,?)");
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setDouble(4, employee.getSalary());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("save error:" + e);
        }
    }

    @Override
    public Employee findById(int id) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setSurname(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setSalary(resultSet.getDouble(5));
                return employee;
            }
        } catch (Exception e) {
            System.out.println("findById error: " + e);
        }
        return null;
    }

    @Override
    public void updateById(Employee employee) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update employee set id=?,name=?,surname=?,age=?,salary=? where id=?");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setDouble(6, employee.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateById error: " + e);
        }
    }

    @Override
    public void deleteById(Employee employee) {
        try (Connection connection = DbConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employee where id=?");
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("deleteById error: " + e);
        }
    }

}
