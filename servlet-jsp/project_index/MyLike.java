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
package project_index;

import com.company.db.DBManager;
import com.company.dto.UDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/MyLike"})
public class MyLike
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        ArrayList<UDto> list = new ArrayList<UDto>();
        String id = request.getParameter("userid");
        String sql = "select * from userlike where userid=? order by uno desc";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, id);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int uno = rs.getInt("uno");
                    String userid = rs.getString("userid");
                    String utitle = rs.getString("utitle");
                    String uwriter = rs.getString("uwriter");
                    String uisbn = rs.getString("uisbn");
                    String udate = rs.getString("udate");
                    String ufile = rs.getString("ufile");
                    String uprice = rs.getString("uprice");
                    list.add(new UDto(uno, userid, utitle, uwriter, uisbn, udate, ufile, uprice));
                }
                request.setAttribute("list", list);
                RequestDispatcher dispatcher = request.getRequestDispatcher("loginBoard/bookLike.jsp");
                dispatcher.forward((ServletRequest)request, (ServletResponse)response);
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    pstmt.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    conn.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        finally {
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Connection conn = null;
        Statement pstmt = null;
        int reset = 0;
        String ok = "";
        String sql = "delete from userlike where uno=?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(request.getParameter("uno")));
                reset = pstmt.executeUpdate();
                if (reset != -1) {
                    ok = "삭제하였습니다.";
                }
                PrintWriter out = response.getWriter();
                out.println("{\"ok\": \"" + ok + "\"}");
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    pstmt.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    conn.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
        finally {
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
