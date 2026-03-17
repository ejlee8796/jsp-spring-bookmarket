/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.RequestDispatcher
 *  javax.servlet.ServletException
 *  javax.servlet.ServletRequest
 *  javax.servlet.ServletResponse
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_list;

import com.company.db.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/BModify"})
public class BModify
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block30: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            int bno = Integer.parseInt(request.getParameter("bno"));
            String uri = request.getParameter("uri");
            Connection conn = null;
            Statement pstmt = null;
            ResultSet reset = null;
            String sql = "select *from " + uri + " where bno = ?";
            RequestDispatcher dispatcher = null;
            try {
                try {
                    DBManager dbmanager = new DBManager();
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    reset = pstmt.executeQuery();
                    if (reset.next()) {
                        request.setAttribute("bno", (Object)request.getParameter("bno"));
                        request.setAttribute("bhit", (Object)("" + reset.getInt("bhit")));
                        request.setAttribute("bname", (Object)reset.getString("bname"));
                        request.setAttribute("bfile", (Object)reset.getString("bfile"));
                        request.setAttribute("bpass", (Object)reset.getString("bpass"));
                        request.setAttribute("btitle", (Object)reset.getString("btitle"));
                        request.setAttribute("bcontent", (Object)reset.getString("bcontent").replace("\n", "<br>"));
                    }
                    if (uri.equals("notice1board1")) {
                        dispatcher = request.getRequestDispatcher(String.valueOf(uri) + "/modify.jsp");
                    }
                    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
                }
                catch (Exception exception) {
                    if (reset != null) {
                        try {
                            reset.close();
                        }
                        catch (Exception exception2) {
                            // empty catch block
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (Exception exception3) {
                            // empty catch block
                        }
                    }
                    if (conn == null) break block30;
                    try {
                        conn.close();
                    }
                    catch (Exception exception4) {}
                }
            }
            finally {
                if (reset != null) {
                    try {
                        reset.close();
                    }
                    catch (Exception exception) {}
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (Exception exception) {}
                }
                if (conn != null) {
                    try {
                        conn.close();
                    }
                    catch (Exception exception) {}
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
