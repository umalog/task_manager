package connection.dao;

import pojo.Task;

import java.util.Set;

public interface TaskDAO {
    public static class TaskDAOException extends Exception {
    }

    Set<Task> getAllTaskOfCompany(String name) throws TaskDAOException;

    void insertAll(Set<Task> tasks) throws TaskDAOException;

    void udateTaskStatus(Task task) throws TaskDAOException;

    void deleteAllTask() throws TaskDAOException;

    Task getTask(int id) throws TaskDAOException;

}
