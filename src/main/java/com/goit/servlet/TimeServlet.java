package com.goit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.TimeZone;

@WebServlet(value = "/*")
public class TimeServlet extends HttpServlet {
    private Date currentDateTimeUTC = Date.from(Instant.now());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentTimeUTC = dateFormat.format(currentDateTimeUTC);

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body>")
                .append("<h1>")
                .append(currentTimeUTC)
                .append(" UTC")
                .append("<h1>")
                .append("<body><html>");

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(htmlBuilder.toString());
        out.close();
    }
}
