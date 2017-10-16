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

public class MainServlet extends HttpServlet {
    private TaskDAO taskDAO = new TaskDAOimpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int taskId = ((Employee) req.getSession().getAttribute("user")).getCurrentTask();
        Task currentTask = null;
        try {
            currentTask = taskDAO.getTask(taskId);
        } catch (TaskDAO.TaskDAOException e) {
            e.printStackTrace();
        }
        if (currentTask != null) {
            req.setAttribute("currentTask", currentTask);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/noTasks.jsp").forward(req, resp);
        }
    }
}
//        List<Student> students = StudentDAO.getAll();
//        req.setAttribute("list", students);
//        req.getRequestDispatcher("/hello.jsp").forward(req, resp);