package com.goit.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/*")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String parameter = req.getParameter("timezone");
        if (req.getParameterMap().containsKey("timezone") && parameter.contains("UTC")) {
            chain.doFilter(req, res);
        } else {
            res.setStatus(400);
            res.setContentType("application/json");
            res.getWriter().write("Invalid timezone");
            res.getWriter().close();
        }
    }
}
