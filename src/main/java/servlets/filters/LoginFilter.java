package servlets.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getSession().getAttribute("isAuth") != null &&
                (Boolean) ((HttpServletRequest) request).getSession().getAttribute("isAuth")) {
            chain.doFilter(request, response); // пропустить
        } else {
            ((HttpServletResponse) response).sendRedirect("/team");
        }
    }  //может быть ошибочно!



    @Override
    public void destroy() {
    }
}
