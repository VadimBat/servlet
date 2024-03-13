package com.goit.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@WebServlet(value = "/*")
public class TimeServlet extends HttpServlet {
    private Date currentDateTimeUTC = Date.from(Instant.now());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><body>")
                .append("<h1>")
                .append(parseCurrentDateTime(req))
                .append(" UTC ")
                .append(parseTimezone(req))
                .append("<h1>")
                .append("<body><html>");

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(htmlBuilder);
        out.close();
    }

    private String parseTimezone(HttpServletRequest req){
        if (req.getParameterMap().containsKey("timezone")){
            return "+" + req.getParameter("timezone");
        }else {
            return "";
        }
    }
    private String parseCurrentDateTime(HttpServletRequest req){
        String parameter = req.getParameter("timezone");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String currentTimeUTC = dateFormat.format(currentDateTimeUTC);
        if (req.getParameterMap().containsKey("timezone")){
            int diffHours = 0;
            if (parameter.length()>1){
                String[] parametersContent = parameter.split(" ");
                diffHours = Integer.parseInt(parametersContent[parametersContent.length-1]);
            } else {
                diffHours = Integer.parseInt(parameter);
            }
            Duration duration = Duration.ofHours(diffHours);
            Date currentDateTime = Date.from(Instant.now().plus(duration));

            return dateFormat.format(currentDateTime);
        }else {
            return currentTimeUTC;
        }
    }
}
