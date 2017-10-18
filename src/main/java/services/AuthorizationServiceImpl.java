package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import org.apache.log4j.Logger;
import pojo.Employee;

public class AuthorizationServiceImpl implements AuthorizationService {
    private static EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(AuthorizationServiceImpl.class);
//    static {
//         PropertyConfigurator.configure("/log4j.properties");
//    }

    @Override
    public Employee auth(String eMail, String password) {
        if (eMail == null || password == null) return null;
        try {
            logger.info(eMail+" – access Granted");
            return employeeDAO.findEmployee(eMail, PasswordEncoder.encode(password));
        } catch (EmployeeDAO.EmployeeDAOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}