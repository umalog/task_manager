package servlets;

import connection.dao.CompanyDAO;
import connection.dao.EmployeeDAO;
import connection.dao.TaskDAO;
import org.apache.log4j.Logger;
import pojo.Employee;
import services.CreateTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class CreateTaskServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CreateTaskServlet.class);
    private CreateTaskService createTS = new CreateTaskService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Employee> freeEmployers = null;
        try {
            freeEmployers = createTS.getFreeEmployers();
        } catch (EmployeeDAO.EmployeeDAOException e) {
            logger.error(e.getStackTrace());
            resp.sendError(500, e.getLocalizedMessage());
        }
        req.setAttribute("freeEmployers", freeEmployers);

        //resp.sendRedirect("/team/createTask.jsp");
        req.getRequestDispatcher("/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("cp1251");
//        resp.setCharacterEncoding("cp1251");
        if (req.getSession().getAttribute("userID") == null) resp.sendRedirect("/team/");
        else {
            String taskName = req.getParameter("taskname");
            String description = req.getParameter("task");
            int executorID = Integer.valueOf(req.getParameter("contractor"));
            int authorID = (int) req.getSession().getAttribute("userID");
            LocalDate deadline = LocalDate.now();
            if (!req.getParameter("dedline").equals("")) {
                deadline = LocalDate.parse(req.getParameter("dedline"));
            }

            try {
                createTS.createThisTask(taskName, description, executorID, authorID, deadline);
            } catch (TaskDAO.TaskDAOException | EmployeeDAO.EmployeeDAOException | CompanyDAO.CompanyDAOException e) {
                logger.error(e.getStackTrace());
                resp.sendError(500, e.getLocalizedMessage());
            }

            resp.sendRedirect("/team/assigned");
        }


    }
}
