package services;

import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Task;

import java.util.Set;
import java.util.TreeSet;

public class CompletedTaskService {
    private static TaskDAO taskDAO = new TaskDAOimpl();
    private static final Logger logger = Logger.getLogger(CompletedTaskService.class);

    public Set<Task> getMyClosedTasks(int employeeID) throws TaskDAO.TaskDAOException {

        return new TreeSet<>(taskDAO.getMyClosedTasks(employeeID));



    }

}
