package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAO.EmployeeDAOException;
import connection.dao.EmployeeDAOimpl;
import connection.dao.TaskDAO;
import connection.dao.TaskDAO.TaskDAOException;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Employee;
import pojo.Task;

public class MainService {
    private static final Logger logger = Logger.getLogger(MainService.class);
    private TaskDAO taskDAO = new TaskDAOimpl();
    private EmployeeDAO employeeDAO = new EmployeeDAOimpl();


    public Task getCurrentTaskInUserID(int userId) {
        Task currentTask = null;
        try {
            Employee employee = employeeDAO.getEmployeeById(userId);
            int currentTaskID = employee.getCurrentTask();
            currentTask = taskDAO.getTask(currentTaskID);
        } catch (TaskDAOException | EmployeeDAOException e) {
            logger.error(e.getMessage());
        }
        return currentTask;
    }


    public Employee getAuthor(Task currentTask) {
        Employee author = null;
        try {
            author = employeeDAO.getEmployeeById(currentTask.getAuthor());
        } catch (EmployeeDAOimpl.EmployeeDAOException e) {
            logger.error(e.getMessage());
        }
        return author;
    }


    public void closeTask(int taskID, int EmployeeId) {
        try {
            taskDAO.closeTask(taskID);
            employeeDAO.closeTask(EmployeeId);
        } catch (TaskDAOException | EmployeeDAOException e) {
            logger.error(e.getMessage());
        }
    }

}
