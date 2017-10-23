package services;

import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Task;

import java.util.Set;
import java.util.TreeSet;

public class AssignedTaskService {

    private TaskDAO taskDAO = new TaskDAOimpl();
    private static final Logger logger = Logger.getLogger(AssignedTaskService.class);

    public Set<Task> getMyAssignedTasks(int employeeID) throws TaskDAO.TaskDAOException {
        return new TreeSet<>(taskDAO.getMyAssignedTasks(employeeID));

    }
}
