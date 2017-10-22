package servlets;

import pojo.Task;
import services.TakeTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class TakeTaskServlet extends HttpServlet {
    TakeTaskService takeTS = new TakeTaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Task> pausedTasks = takeTS.getFreeTasks();
        req.setAttribute("pausedTasks", pausedTasks);
        req.getRequestDispatcher("/takeTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("taskID") == null) resp.sendRedirect("/team/main");
        int taskID = Integer.valueOf(req.getParameter("taskID"));
        int userID = (int) req.getSession().getAttribute("userID");
        if (takeTS.updateTaskStatus(userID, taskID)){
            resp.sendRedirect("/team/main");
        }
        resp.sendRedirect("/team/take");


    }
}

