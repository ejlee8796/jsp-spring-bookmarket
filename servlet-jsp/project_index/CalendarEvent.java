/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Calendar_ajax"})
public class CalendarEvent
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        Calendar today = Calendar.getInstance();
        int year = today.get(1);
        int month = today.get(2);
        if (request.getParameter("year") != null) {
            year = Integer.parseInt(request.getParameter("year"));
            month = Integer.parseInt(request.getParameter("month"));
            if (month == 12) {
                ++year;
                month = 0;
            } else if (month == -1) {
                --year;
                month = 11;
            }
        }
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int startweek = cal.get(7);
        int lastday = cal.getActualMaximum(5);
        cal.set(year, month, lastday);
        int lastweek = cal.get(7);
        PrintWriter out = response.getWriter();
        out.println("{\"startweek\": " + (startweek - 1) + ",\"lastday\": " + lastday + ",\"lastweek\": " + (7 - lastweek) + ", \"month\": " + month + ", \"year\": " + year + "}");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
