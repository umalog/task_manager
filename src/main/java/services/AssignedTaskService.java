package services;

import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Task;

import java.util.Set;

public class AssignedTaskService {

    private TaskDAO taskDAO = new TaskDAOimpl();
    private static final Logger logger = Logger.getLogger(AssignedTaskService.class);

    public Set<Task> getMyAssignedTasks(int employeeID) {
        try {
            return taskDAO.getMyAssignedTasks(employeeID);
        } catch (TaskDAO.TaskDAOException e) {
            logger.error(e.getMessage());

        }
        return null;
    }
}
