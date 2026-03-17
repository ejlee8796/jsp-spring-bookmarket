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
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/Breply"})
public class Breply
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getParameter("uri");
        int bstep = Integer.parseInt(request.getParameter("bstep"));
        int bgroup = Integer.parseInt(request.getParameter("bgroup"));
        int newBstep = bstep;
        String bname = request.getParameter("name");
        String bpassword = request.getParameter("password");
        String btitle = request.getParameter("title");
        String bcontent = request.getParameter("content").replace("<br>", "\r\n");
        int bdepth = 0;
        InetAddress local = InetAddress.getLocalHost();
        String ip = local.getHostAddress();
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rset = null;
        int reset = 0;
        String sql1 = "select *from " + uri + " where bstep = ? and bgroup = ?";
        String sql3 = "update " + uri + " set bstep = bstep-1 where (?-1000) < bstep and bstep < ?";
        String sql2 = "insert into " + uri + " (bname, bpass, btitle, bcontent, bfile, bgroup, bstep, bip ) values (?,?,?,?,?,?,?,?)";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql1);
                pstmt.setInt(1, bstep);
                pstmt.setInt(2, bgroup);
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    bdepth = rset.getInt("bdepth") + 1;
                    bstep = rset.getInt("bgroup") * 1000 - bdepth;
                }
                pstmt.close();
                pstmt = conn.prepareStatement(sql3);
                pstmt.setInt(1, newBstep);
                pstmt.setInt(2, newBstep);
                reset = pstmt.executeUpdate();
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, bname);
                pstmt.setString(2, bpassword);
                pstmt.setString(3, btitle);
                pstmt.setString(4, bcontent);
                pstmt.setInt(5, bgroup);
                pstmt.setInt(6, bstep);
                pstmt.setInt(7, bdepth);
                pstmt.setString(8, ip);
                reset = pstmt.executeUpdate();
                if (reset != -1 && uri.equals("notice1board1")) {
                    response.sendRedirect("footer_1.jsp?reset=" + reset);
                }
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
}
