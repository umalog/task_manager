package servlets;

import org.apache.log4j.Logger;
import pojo.Employee;
import services.AuthorizationService;
import services.AuthorizationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    private static AuthorizationService as = new AuthorizationServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        /* AuthorizationService */
        Employee employee;
        if ((employee = as.auth(login, password))!=null) {
            logger.info(login+" – access Granted");
            req.getSession().setAttribute("isAuth", true);
            req.getSession().setAttribute("user", employee);
            req.getRequestDispatcher("/main").forward(req, resp);
        } else {

            getServletContext().getRequestDispatcher("/").forward(req, resp);
        }
    }
}

