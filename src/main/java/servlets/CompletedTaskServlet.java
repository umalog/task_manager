package servlets;

import connection.dao.TaskDAO;
import org.apache.log4j.Logger;
import pojo.Task;
import services.CompletedTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CompletedTaskServlet extends HttpServlet {
    CompletedTaskService completedTS = new CompletedTaskService();
    private static final Logger logger = Logger.getLogger(CompletedTaskServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userID") != null) {
            int userID = (int) req.getSession().getAttribute("userID");
            Set<Task> myClosedTasks = null;
            try {
                myClosedTasks = completedTS.getMyClosedTasks(userID);
            } catch (TaskDAO.TaskDAOException e) {
                logger.error(e.getStackTrace());
                resp.sendError(500, e.getLocalizedMessage());
            }
            req.setAttribute("myClosedTasks", myClosedTasks);
            req.getRequestDispatcher("/completedTasks.jsp").forward(req, resp);


        } else resp.sendRedirect("/team");
    }
}
