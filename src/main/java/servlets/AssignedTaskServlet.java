package servlets;

import connection.dao.TaskDAO;
import org.apache.log4j.Logger;
import pojo.Task;
import services.AssignedTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class AssignedTaskServlet extends HttpServlet {

    private static AssignedTaskService assignedTS = new AssignedTaskService();
    private static final Logger logger = Logger.getLogger(AssignedTaskServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("userID") != null) {
            int userID = (int) req.getSession().getAttribute("userID");
            Set<Task> myAssignedTasks = null;
            try {
                myAssignedTasks = assignedTS.getMyAssignedTasks(userID);
            } catch (TaskDAO.TaskDAOException e) {
                logger.error(e.getStackTrace());
                resp.sendError(500, e.getLocalizedMessage());
            }
            req.setAttribute("myAssignedTasks", myAssignedTasks);

            req.getRequestDispatcher("/assignedTasks.jsp").forward(req, resp);


        } else {
            logger.info("кто-то лезет не туда: assignedTasks");
            resp.sendRedirect("/team");
        }
    }
}
