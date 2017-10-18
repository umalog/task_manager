package servlets;

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
import java.util.Set;

public class CompletedTaskServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAOimpl();
    private static final Logger logger = Logger.getLogger(CompletedTaskServlet.class);

//    static {
//          PropertyConfigurator.configure("/log4j.properties");
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            int employeeID = ((Employee) req.getSession().getAttribute("user")).getEmployeeID();
            try {
                Set<Task> myClosedTasks = taskDAO.getMyClosedTasks(employeeID);
                req.setAttribute("myClosedTasks", myClosedTasks);
            } catch (TaskDAO.TaskDAOException e) {
                logger.error(e.getMessage());
            }
            req.getRequestDispatcher("/completedTasks.jsp").forward(req, resp);
            getServletContext().getRequestDispatcher("/").forward(req, resp);

        }
    }
}
