package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Employee;
import pojo.Task;

import java.util.Set;
import java.util.TreeSet;

public class CompletedTaskService {
    private static final TaskDAO taskDAO = new TaskDAOimpl();
    private static final EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(CompletedTaskService.class);

    public Set<Task> getMyClosedTasks(int employeeID) throws TaskDAO.TaskDAOException {

        return new TreeSet<>(taskDAO.getMyClosedTasks(employeeID));

    }

    public String getAuthor(int authorId) throws EmployeeDAO.EmployeeDAOException {
        Employee author = employeeDAO.getEmployeeById(authorId);
        String fio = author.getEmployeeName()+" / "+author.getEmployeePosition();
        return fio;
    }
}
