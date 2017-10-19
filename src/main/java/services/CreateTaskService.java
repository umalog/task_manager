package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import org.apache.log4j.Logger;
import pojo.Employee;

import java.util.Set;

public class CreateTaskService {
    private static EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(CreateTaskService.class);


    public Set<Employee> getFreeEmployers(){
        try {
            return employeeDAO.getFreeEmployers();
        } catch (EmployeeDAOimpl.EmployeeDAOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
