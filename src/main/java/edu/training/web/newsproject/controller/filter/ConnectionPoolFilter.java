package edu.training.web.newsproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

public class ConnectionPoolFilter extends HttpFilter implements Filter {

    public ConnectionPoolFilter() {
        super();
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletContext context = request.getServletContext();
        String poolError = (String) context.getAttribute("poolError");

        if (poolError != null) {
            request.setAttribute("Info", "The application is temporarily down. come back later");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
