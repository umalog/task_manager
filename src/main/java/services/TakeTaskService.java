package services;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Task;

import java.util.Set;

public class TakeTaskService {

    private static TaskDAO taskDAO = new TaskDAOimpl();
    private static EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(TakeTaskService.class);

    public Set<Task> getFreeTasks() throws TaskDAO.TaskDAOException {

        return taskDAO.getPausedTasks();

    }

    public boolean updateTaskStatus(int executor, int taskId) throws EmployeeDAO.EmployeeDAOException, TaskDAO.TaskDAOException {


        if (employeeDAO.getEmployeeById(executor).getCurrentTask() == 0) {
            taskDAO.udateTaskStatus(executor, taskId);
            employeeDAO.takeTask(taskId, executor);
            return true;
        }else return false;

    }
}
