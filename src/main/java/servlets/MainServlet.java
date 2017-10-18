package servlets;

import connection.dao.EmployeeDAO;
import connection.dao.EmployeeDAOimpl;
import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import org.apache.log4j.Logger;
import pojo.Employee;
import pojo.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAOimpl();
    private EmployeeDAO employeeDAO = new EmployeeDAOimpl();
    private static final Logger logger = Logger.getLogger(MainServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doIt(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doIt(req,resp);
    }

    private void doIt(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int taskId = ((Employee) req.getSession().getAttribute("user")).getCurrentTask();
        Task currentTask = null;
        try {
            currentTask = taskDAO.getTask(taskId);
        } catch (TaskDAO.TaskDAOException e) {
            logger.error(e.getMessage());
        }
        if (currentTask != null) {
            Employee author = null;
            try {
                author = employeeDAO.getEmployeeById(currentTask.getAuthor());
            } catch (EmployeeDAOimpl.EmployeeDAOException e) {
                logger.error(e.getMessage());
            }
            req.setAttribute("author", author);
            req.setAttribute("currentTask", currentTask);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/noTasks.jsp").forward(req, resp);
        }
    }
}
