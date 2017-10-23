package servlets;

import connection.dao.EmployeeDAO;
import connection.dao.TaskDAO;
import org.apache.log4j.Logger;
import pojo.Task;
import services.TakeTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class TakeTaskServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TakeTaskServlet.class);
    TakeTaskService takeTS = new TakeTaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Task> pausedTasks = null;
        try {
            pausedTasks = takeTS.getFreeTasks();
        } catch (TaskDAO.TaskDAOException e) {
            logger.error(e.getStackTrace());
            resp.sendError(500, e.getLocalizedMessage());
        }
        req.setAttribute("pausedTasks", pausedTasks);
        req.getRequestDispatcher("/takeTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("taskID") == null) resp.sendRedirect("/team");
        int taskID = Integer.valueOf(req.getParameter("taskID"));
        int userID = (int) req.getSession().getAttribute("userID");
        try {
            if (takeTS.updateTaskStatus(userID, taskID)){
                resp.sendRedirect("/team/main");
            }else resp.sendRedirect("/team/take");
        } catch (EmployeeDAO.EmployeeDAOException | TaskDAO.TaskDAOException e) {
            logger.error(e.getStackTrace());
            resp.sendError(500, e.getLocalizedMessage());
        }


    }
}

