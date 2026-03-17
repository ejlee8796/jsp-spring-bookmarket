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
package project_list;

import com.company.db.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/BDelete"})
public class BDelete
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block29: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            int bno = Integer.parseInt(request.getParameter("bno"));
            String uri = request.getParameter("uri");
            Connection conn = null;
            Statement pstmt = null;
            ResultSet reset = null;
            int result = 0;
            String sql1 = "select bpass from " + uri + " where bno = ?";
            String sql2 = "delete from " + uri + " where bno = ?";
            try {
                try {
                    DBManager dbmanager = new DBManager();
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(1, bno);
                    reset = pstmt.executeQuery();
                    reset.close();
                    pstmt.close();
                    pstmt = conn.prepareStatement(sql2);
                    pstmt.setInt(1, bno);
                    result = pstmt.executeUpdate();
                    if (uri.equals("notice1board1")) {
                        response.sendRedirect(String.valueOf(uri) + "/footer_1.jsp?result=" + result);
                    }
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
                    if (conn == null) break block29;
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
    }
}
