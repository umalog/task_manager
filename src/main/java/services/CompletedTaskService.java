package services;

import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Task;

import java.util.Set;

public class CompletedTaskService {
    private static TaskDAO taskDAO = new TaskDAOimpl();
    private static final Logger logger = Logger.getLogger(CompletedTaskService.class);

    public Set<Task> getMyClosedTasks(int employeeID) {

        try {
            return taskDAO.getMyClosedTasks(employeeID);
        } catch (TaskDAO.TaskDAOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
