package connection.dao;

import pojo.Task;

import java.util.Set;

public interface TaskDAO {
    public static class TaskDAOException extends Exception {
    }

    Set<Task> getAllTaskOfCompany(String name) throws TaskDAOException;

    void insertAll(Set<Task> tasks) throws TaskDAOException;

    void insertTask(Task task) throws TaskDAOException;

    public void udateTaskStatus(int employee, int taskId) throws TaskDAOException;

    void deleteAllTask() throws TaskDAOException;

    Task getTask(int id) throws TaskDAOException;

    Set<Task> getMyClosedTasks(int id) throws TaskDAOException;

    Set<Task> getMyAssignedTasks(int id) throws TaskDAOException;

    Set<Task> getPausedTasks() throws TaskDAOException;

    public void closeTask(int taskId) throws TaskDAOException;

}
