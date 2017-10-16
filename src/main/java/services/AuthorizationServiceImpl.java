package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import pojo.Employee;

public class AuthorizationServiceImpl implements AuthorizationService {
    private static EmployeeDAO employeeDAO = new EmployeeDAOimpl();

    @Override
    public Employee auth(String eMail, String password) {
        if (eMail == null || password == null) return null;
        try {
            return employeeDAO.findEmployee(eMail, PasswordEncoder.encode(password));
        } catch (EmployeeDAO.EmployeeDAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}