package connection.dao;

import pojo.Employee;

import java.util.Set;

public interface EmployeeDAO {

    class EmployeeDAOException extends Exception {
    }


    Set<Employee> getAllEmployee(String companyName) throws EmployeeDAOimpl.EmployeeDAOException;

    Set<Employee> getFreeEmployers() throws EmployeeDAOimpl.EmployeeDAOException;


    void insertAllEmployee(Set<Employee> emp) throws EmployeeDAOimpl.EmployeeDAOException;

    void insertEmployee(Employee employee) throws EmployeeDAOimpl.EmployeeDAOException;

    Employee getEmployeeById(Integer id) throws EmployeeDAOimpl.EmployeeDAOException;

    void deleteAllEmployee() throws EmployeeDAOException;

    Employee findEmployee(String eMail, String password)throws EmployeeDAOException;

    void closeTask(Integer id)throws EmployeeDAOException;
}
