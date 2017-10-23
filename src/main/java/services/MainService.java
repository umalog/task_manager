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


    public Task getCurrentTaskInUserID(int userId) throws EmployeeDAOException, TaskDAOException {
        Task currentTask = null;
        Employee employee = employeeDAO.getEmployeeById(userId);
        int currentTaskID = employee.getCurrentTask();
        currentTask = taskDAO.getTask(currentTaskID);
        return currentTask;
    }


    public Employee getAuthor(Task currentTask) throws EmployeeDAOException {
        Employee author = null;

        author = employeeDAO.getEmployeeById(currentTask.getAuthor());

        return author;
    }


    public void closeTask(int taskID, int EmployeeId) throws TaskDAOException, EmployeeDAOException {

        taskDAO.closeTask(taskID);
        employeeDAO.closeTask(EmployeeId);

        String mail = employeeDAO.getEmployeeById(taskDAO.getTask(taskID).getAuthor()).geteMail();
        SendMail.sendForCloseTask(mail, taskID);

    }

}
