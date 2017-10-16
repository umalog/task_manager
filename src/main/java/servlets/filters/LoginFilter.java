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
        Boolean isAuth = (Boolean) ((HttpServletRequest) request).getSession().getAttribute("isAuth");
        if (isAuth) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/team");
        }
    }

    @Override
    public void destroy() {
    }
}
