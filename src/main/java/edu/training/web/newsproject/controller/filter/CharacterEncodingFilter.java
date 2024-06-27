package edu.training.web.newsproject.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

@WebFilter()
public class CharacterEncodingFilter extends HttpServlet implements Filter {

    public CharacterEncodingFilter() {
        super();
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        // Initialization code
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("EncodingFilter doFilter called");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        System.out.println("Request encoding: " + request.getCharacterEncoding());
        System.out.println("Response encoding: " + response.getCharacterEncoding());
        chain.doFilter(request, response);
    }

    public void destroy() {
        System.out.println("EncodingFilter destroyed");
    }
}
