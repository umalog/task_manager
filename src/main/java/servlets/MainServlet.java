package servlets;

import org.apache.log4j.Logger;
import pojo.Employee;
import pojo.Task;
import services.MainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    MainService mainService = new MainService();
    private static final Logger logger = Logger.getLogger(MainServlet.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int  userID = (int)req.getSession().getAttribute("userID");
        if (userID==0)resp.sendRedirect("/team");

        Task currentTask = mainService.getCurrentTaskInUserID(userID);

        if (currentTask != null) {
            Employee author = mainService.getAuthor(currentTask);

            req.setAttribute("author", author);
            req.setAttribute("currentTask", currentTask);
            req.getRequestDispatcher("/main.jsp").forward(req, resp);

        } else {
            req.getRequestDispatcher("/noTasks.jsp").forward(req, resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Закрываем Задачу!!! \"MainServlet.doPost()\"");
        int  userID = (int)req.getSession().getAttribute("userID");
        int taskID = Integer.valueOf(req.getParameter("taskID"));
        mainService.closeTask(taskID, userID);
        resp.sendRedirect("/team/main");

    }

}
