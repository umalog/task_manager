package servlets;

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
    private CreateTaskService createTS = new CreateTaskService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Employee> freeEmployers = createTS.getFreeEmployers();
        req.setAttribute("freeEmployers", freeEmployers);

        //resp.sendRedirect("/team/createTask.jsp");
        req.getRequestDispatcher("/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("cp1251");
        resp.setCharacterEncoding("cp1251");
        String taskName = req.getParameter("taskname");
        String description = req.getParameter("task");
        int executorID = Integer.valueOf(req.getParameter("contractor"));
        int authorID = (int) req.getSession().getAttribute("userID");
        LocalDate deadline = LocalDate.now();
        if (!req.getParameter("dedline").equals("")) {
            deadline = LocalDate.parse(req.getParameter("dedline"));
        }

        createTS.createThisTask(taskName, description, executorID, authorID, deadline);

        resp.sendRedirect("/team/assigned");


    }
}
