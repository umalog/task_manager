package servlets;

import pojo.Employee;
import services.CreateTaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CreateTaskServlet extends HttpServlet {
    CreateTaskService createTS = new CreateTaskService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Employee> freeEmployers = createTS.getFreeEmployers();
        req.setAttribute("freeEmployers", freeEmployers);

        req.getRequestDispatcher("/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {




    }
}
