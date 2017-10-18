package connection.dao;


import connection.ConnectionManager;
import connection.ConnectionPoolPostgreSql;
import org.apache.log4j.Logger;
import pojo.Company;
import pojo.Employee;
import pojo.Task;
import pojo.TaskStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CompanyDAOimpl implements CompanyDAO {


    private static ConnectionManager manager;
    private static final Logger logger = Logger.getLogger(CompanyDAOimpl.class);

    static {
        //manager = ConnectionManagerPostgreSQL.getInstance();
        manager = new ConnectionPoolPostgreSql();
    }


    /* getAll() исправлено на JOIN */
    public Company getByName(String name) throws CompanyDAOException, TaskDAOimpl.TaskDAOException, EmployeeDAOimpl.EmployeeDAOException {
        Company com;
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement
                    ("SELECT * FROM companies comp JOIN employee emp ON comp.name = emp.company " +
                            "JOIN task bag ON comp.name = bag.company WHERE name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            com = parseFromResultSet(resultSet);

        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CompanyDAOException();
        }
        return com;
    }

    private Company parseFromResultSet(ResultSet resultSet) throws SQLException {
        String tmpName = null;
        int tmpEmpCount = 0;
        int tmpTaskCount = 0;
        Set<Employee> workers = new HashSet<>();
        Set<Task> tasks = new HashSet<>();

        while (resultSet.next()) {

            if (!Objects.equals(resultSet.getString("name"), tmpName))
                tmpName = resultSet.getString("name");
            if (resultSet.getInt("employee_count") != tmpEmpCount)
                tmpEmpCount = resultSet.getInt("employee_count");
            if (resultSet.getInt("task_count") != tmpTaskCount)
                tmpTaskCount = resultSet.getInt("task_count");

            if (resultSet.getInt("id") != 0) {
                Employee emp = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("position"),
                        resultSet.getString("e_mail"),
                        resultSet.getInt("current_task"),
                        resultSet.getString("company"),
                        resultSet.getString("password"));
                if (!workers.contains(emp)) workers.add(emp);
            }
            if (resultSet.getInt("task_id") != 0) {
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
                if (!tasks.contains(task)) tasks.add(task);
            }
        }
        return new Company(tmpName, tmpEmpCount, tmpTaskCount, workers, tasks);
    }


    public void deleteAll() throws CompanyDAOException, EmployeeDAOimpl.EmployeeDAOException, TaskDAOimpl.TaskDAOException {
        try {
            new TaskDAOimpl().deleteAllTask();
            new EmployeeDAOimpl().deleteAllEmployee();
            //  ON DELETE CASCADE
            manager.getConnection().createStatement().execute
                    ("DELETE FROM umalog.public.companies");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CompanyDAOException();
        }
    }

    /* пароли Set<Employee> шифруются при сохранении в БД */
    public void insertCompany(Company company) throws CompanyDAOException, TaskDAOimpl.TaskDAOException, EmployeeDAOimpl.EmployeeDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO umalog.public.companies" +
                            "(name, employee_count, task_count) VALUES(?, ?, ?)");
            statement.setString(1, company.companyName);
            statement.setInt(2, company.getEmployeeIDCounter());
            statement.setInt(3, company.getTaskIDCounter());
            statement.executeUpdate();
            new EmployeeDAOimpl().insertAllEmployee(company.getWorkers());
            new TaskDAOimpl().insertAll(company.getAllTask());
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CompanyDAOException();
        }

    }
}
