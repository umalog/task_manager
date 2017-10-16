package connection.dao;

import connection.ConnectionManager;
import connection.ConnectionManagerPostgreSQL;
import pojo.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static services.PasswordEncoder.encode;

public class EmployeeDAOimpl implements EmployeeDAO {

    private static ConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }

    @Override
    public Set<Employee> getAllEmployee(String companyName) throws EmployeeDAOException {
        Set<Employee> emp = new HashSet<>();
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("SELECT * FROM umalog.public.employee WHERE company = ?");
            statement.setString(1, companyName);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                emp.add(
                        new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("full_name"),
                                resultSet.getString("position"),
                                resultSet.getString("e_mail"),
                                resultSet.getInt("current_task"),
                                resultSet.getString("company"),
                                resultSet.getString("password")
                        )
                );
            }
            return emp;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException();
        }
    }

    @Override
    public void insertAllEmployee(Set<Employee> emp) throws EmployeeDAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(
                    "INSERT INTO umalog.public.employee" +
                            "(id, full_name, position, e_mail, current_task, company, password)"
                            + "VALUES(?, ?, ?, ?, ?, ?, ?)");
            for (Employee st : emp) {
                statement.setInt(1, st.getEmployeeID());
                statement.setString(2, st.getEmployeeName());
                statement.setString(3, st.getEmployeePosition());
                statement.setString(4, st.getEMail());
                statement.setInt(5, st.getCurrentTask());
                statement.setString(6, st.getCompany());
                /* пароли шифруются перед сохранением в БД */
                statement.setString(7, encode(st.getPassword()));
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException();
        }
    }

    @Override
    public void insertEmployee(Employee employee) throws EmployeeDAOException {
        if (employee != null) {
            try {
                PreparedStatement statement = manager.getConnection().prepareStatement(
                        "INSERT INTO umalog.public.employee (id, full_name, position, e_mail, current_task, company, password)"
                                + "VALUES(?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, employee.getEmployeeID());
                statement.setString(2, employee.getEmployeeName());
                statement.setString(3, employee.getEmployeePosition());
                statement.setString(4, employee.getEMail());
                statement.setInt(5, employee.getCurrentTask());
                statement.setString(6, employee.getCompany());
                /* пароль шифруется перед сохранением в БД */
                statement.setString(7, encode(employee.getPassword()));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new EmployeeDAOException();
            }
        }
    }

    @Override
    public Employee getEmployeeById(Integer id) throws EmployeeDAOException {
        Employee employee = null;
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("SELECT * FROM employee WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("position"),
                        resultSet.getString("e_mail"),
                        resultSet.getInt("current_task"),
                        resultSet.getString("company"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException();
        }
        return employee;

    }

    @Override
    public void deleteAllEmployee() throws EmployeeDAOException {
        try {
            manager.getConnection().createStatement().execute("DELETE FROM umalog.public.employee");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException();
        }
    }

    @Override
    public Employee findEmployee(String eMail, String password) throws EmployeeDAOException {
        Employee employee=null;
        try {
            PreparedStatement statement = manager.getConnection().
                    prepareStatement("SELECT * FROM employee WHERE e_mail = ? AND password = ?");
            statement.setString(1, eMail);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("position"),
                        resultSet.getString("e_mail"),
                        resultSet.getInt("current_task"),
                        resultSet.getString("company"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeDAOException();
        }
        return employee;
    }
}
