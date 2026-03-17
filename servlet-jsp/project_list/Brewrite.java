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
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Brewrite"})
public class Brewrite
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getParameter("uri");
        int bstep = Integer.parseInt(request.getParameter("bstep"));
        int bgroup = Integer.parseInt(request.getParameter("bgroup"));
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rset = null;
        String sql1 = "select *from " + uri + " where bstep = ? and bgroup = ?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql1);
                pstmt.setInt(1, bstep);
                pstmt.setInt(2, bgroup);
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    request.setAttribute("bstep", (Object)rset.getInt("bstep"));
                    request.setAttribute("bgroup", (Object)rset.getInt("bgroup"));
                    request.setAttribute("btitle", (Object)rset.getString("btitle"));
                    request.setAttribute("bcontent", (Object)rset.getString("bcontent"));
                }
                RequestDispatcher dispatcher = null;
                if (uri.equals("notice1board1")) {
                    dispatcher = request.getRequestDispatcher(String.valueOf(uri) + "/reply.jsp");
                }
                dispatcher.forward((ServletRequest)request, (ServletResponse)response);
            }
            catch (Exception exception) {
                try {
                    rset.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    pstmt.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        finally {
            try {
                rset.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pstmt.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
