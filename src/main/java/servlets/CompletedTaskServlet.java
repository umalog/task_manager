package servlets;

import connection.dao.TaskDAO;
import connection.dao.TaskDAOimpl;
import pojo.Employee;
import pojo.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CompletedTaskServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAOimpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeID = ((Employee) req.getSession().getAttribute("user")).getEmployeeID();
        try {
            Set<Task> myClosedTasks = taskDAO.getMyClosedTasks(employeeID);
            req.setAttribute("myClosedTasks", myClosedTasks);
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/completedTasks.jsp").forward(req, resp);
    }
}
