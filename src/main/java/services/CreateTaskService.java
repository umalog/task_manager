package services;

import connection.dao.*;
import org.apache.log4j.Logger;
import pojo.Company;
import pojo.Employee;
import pojo.Task;

import java.time.LocalDate;
import java.util.Set;

public class CreateTaskService {
    private static CompanyDAO companyDAO = new CompanyDAOimpl();
    private static EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(CreateTaskService.class);
    private static TaskDAO taskDAO = new TaskDAOimpl();

    public Set<Employee> getFreeEmployers() throws EmployeeDAO.EmployeeDAOException {

        return employeeDAO.getFreeEmployers();

    }

    public void createThisTask(String taskName, String description, int executorID, int authorID, LocalDate deadline) throws TaskDAO.TaskDAOException, EmployeeDAO.EmployeeDAOException, CompanyDAO.CompanyDAOException {

        Employee author = employeeDAO.getEmployeeById(authorID);
        Company company = companyDAO.getByName(author.getCompany());
        Task task;
        if (executorID == 0) {
            task = new Task(taskName, description, author, deadline, company);
        } else {
            Employee executor = employeeDAO.getEmployeeById(executorID);
            task = new Task(taskName, description, executor, author, deadline, company);

            SendMail.sendNotification(executor.geteMail(), taskName, description);
        }
        taskDAO.insertTask(task);
        companyDAO.updateCompany(company);
        employeeDAO.takeTask(task.getTaskID(), executorID);


    }
}
