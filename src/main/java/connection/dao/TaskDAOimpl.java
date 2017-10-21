package connection.dao;


import connection.ConnectionManager;
import connection.ConnectionPoolPostgreSql;
import org.apache.log4j.Logger;
import pojo.Task;
import pojo.TaskStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class TaskDAOimpl implements TaskDAO {

    private static ConnectionManager manager;
    private static final Logger logger = Logger.getLogger(TaskDAOimpl.class);

    static {
//        manager = ConnectionManagerPostgreSQL.getInstance();
        manager = new ConnectionPoolPostgreSql();
    }

    public Set<Task> getAllTaskOfCompany(String name) throws TaskDAOException {
        Set<Task> tasks = new HashSet<>();
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM umalog.public.task WHERE company = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getDate("close_date") != null) {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("close_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                } else if (resultSet.getDate("start_date") != null) {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                } else {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                }

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
        return tasks;
    }

    @Override
    public Set<Task> getMyClosedTasks(int id) throws TaskDAOException {
        Set<Task> tasks = new HashSet<>();
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM task WHERE executor = ? AND status = ?");
            statement.setInt(1, id);
            statement.setString(2, "Closed");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("description"),
                        resultSet.getInt("executor"),
                        resultSet.getInt("author"),
                        resultSet.getDate("start_date").toLocalDate(),
                        resultSet.getDate("close_date").toLocalDate(),
                        resultSet.getDate("deadline").toLocalDate(),
                        TaskStatus.valueOf(resultSet.getString("status")),
                        resultSet.getString("company"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
        return tasks;
    }

    @Override
    public Set<Task> getMyAssignedTasks(int id) throws TaskDAOException {
        Set<Task> tasks = new HashSet<>();
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM task WHERE author = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getDate("close_date") != null) {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("close_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                } else if (resultSet.getDate("start_date") != null) {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                } else {
                    tasks.add(new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    ));
                }

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
        return tasks;
    }

    public void insertAll(Set<Task> tasks) throws TaskDAOException {
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO umalog.public.task" +
                            "(task_id, task_name, description, executor, author, start_date, close_date, deadline, status, company)"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            for (Task task : tasks) {
                statement.setInt(1, task.getTaskID());
                statement.setString(2, task.getTaskName());
                statement.setString(3, task.getDescription());
                statement.setInt(4, task.getExecutor());
                statement.setInt(5, task.getAuthor());
                if (task.getWorkStartDate() != null)
                    statement.setDate(6, Date.valueOf(task.getWorkStartDate()));
                else statement.setDate(6, null);
                if (task.getClosingDate() != null)
                    statement.setDate(7, Date.valueOf(task.getClosingDate()));
                else statement.setDate(7, null);
                statement.setDate(8, Date.valueOf(task.getDeadline()));
                statement.setObject(9, task.getStatus().toString());
                statement.setString(10, task.getCompany());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
    }

    public void udateTaskStatus(Task task) throws TaskDAOException {
        try (Connection connection = manager.getConnection()){
            if (task.getClosingDate() == null) { //Взять задачу
                PreparedStatement statement = connection.prepareStatement
                        ("UPDATE umalog.public.task SET executor = ?, start_date = ? WHERE task_id = ?");
                statement.setInt(1, task.getExecutor());
                statement.setDate(2, Date.valueOf(task.getWorkStartDate()));
                statement.setInt(3, task.getTaskID());
                statement.executeUpdate();
            }
//            else {
//                PreparedStatement statement = manager.getConnection().prepareStatement
//                        ("UPDATE umalog.public.task SET close_date = ?, status = ? WHERE task_id = ?");
//                statement.setDate(1, Date.valueOf(task.getClosingDate()));
//                statement.setString(2, "Closed");
//                statement.setInt(3, task.getTaskID());
//                statement.executeUpdate();
//            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
    }

    public void closeTask(int taskId) throws TaskDAOException {
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement
                    ("UPDATE umalog.public.task SET close_date = ?, status = ? WHERE task_id = ?");
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setString(2, "Closed");
            statement.setInt(3, taskId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
    }


    public void deleteAllTask() throws TaskDAOException {
        try (Connection connection = manager.getConnection()){
            connection.createStatement().execute("DELETE FROM umalog.public.task");

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
    }

    @Override
    public Task getTask(int id) throws TaskDAOException {
        try (Connection connection = manager.getConnection()){
            PreparedStatement statement = connection.prepareStatement
                    ("SELECT * FROM task WHERE task_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getDate("close_date") != null) {
                    return new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("close_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    );
                } else if (resultSet.getDate("start_date") != null) {
                    return new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("start_date").toLocalDate(),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    );
                } else {
                    return new Task(
                            resultSet.getInt("task_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("description"),
                            resultSet.getInt("executor"),
                            resultSet.getInt("author"),
                            resultSet.getDate("deadline").toLocalDate(),
                            TaskStatus.valueOf(resultSet.getString("status")),
                            resultSet.getString("company")
                    );
                }

            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new TaskDAOException();
        }
        return null;
    }

}
