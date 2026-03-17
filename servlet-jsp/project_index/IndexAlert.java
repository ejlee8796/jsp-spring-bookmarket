/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.Cookie
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_index;

import com.company.db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/indexAlert"})
public class IndexAlert
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subpop = request.getParameter("subpop");
        Cookie cookie = new Cookie("subpop", subpop);
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String uisbn = request.getParameter("uisbn");
        String utitle = request.getParameter("utitle");
        String uwriter = request.getParameter("uwriter");
        String ufile = request.getParameter("ufile");
        String userid = request.getParameter("userid");
        String uprice = request.getParameter("uprice");
        String ok = "이미 관심설정하였습니다.";
        if (this.SelectLike(userid, uisbn)) {
            ok = this.InsertLike(userid, utitle, uwriter, uisbn, ufile, uprice);
        }
        PrintWriter out = response.getWriter();
        out.println("{\"ok\": \"" + ok + "\"}");
    }

    public boolean SelectLike(String userid, String uisbn) {
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rs = null;
        boolean result = true;
        String sql = "select * from userlike where userid=? and uisbn=?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userid);
                pstmt.setString(2, uisbn);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    result = false;
                }
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
        return result;
    }

    public String InsertLike(String userid, String utitle, String uwriter, String uisbn, String ufile, String uprice) {
        Connection conn = null;
        Statement pstmt = null;
        int reset = 0;
        String alert = "";
        String sql = "insert into userlike (userid, utitle, uwriter, uisbn, ufile, uprice) values (?,?,?,?,?,?)";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, userid);
                pstmt.setString(2, utitle);
                pstmt.setString(3, uwriter);
                pstmt.setString(4, uisbn);
                pstmt.setString(5, ufile);
                pstmt.setString(6, uprice);
                reset = pstmt.executeUpdate();
                if (reset != -1) {
                    alert = "관심설정완료";
                }
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
        return alert;
    }
}
