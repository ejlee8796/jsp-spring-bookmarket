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
 *  javax.servlet.http.HttpSession
 */
package project_login;

import com.company.db.DBManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/loginProcess"})
public class loginProcess
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block24: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            HttpSession session = request.getSession();
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet reset = null;
            String id = request.getParameter("mbid");
            String sql = "select * from member where mbid = ?";
            DBManager dbmanager = new DBManager();
            PrintWriter out = response.getWriter();
            try {
                try {
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    reset = pstmt.executeQuery();
                    if (reset.next()) {
                        String email = reset.getString("mbemail");
                        Cookie cookie1 = new Cookie("id", id);
                        cookie1.setMaxAge(86400);
                        response.addCookie(cookie1);
                        session.setAttribute("id", (Object)id);
                        session.setAttribute("email", (Object)email);
                        session.setMaxInactiveInterval(86400);
                        out.print("<script>alert('반갑습니다. 회원님.'); location.href='http://adnerwin.cafe24.com/project0709/cal/calendar?id=" + id + "';</script>");
                        break block24;
                    }
                    out.print("<script>alert('아이디와 비밀번호를 확인해주세요.'); location.href='javascript:history.go(-1)';</script>");
                }
                catch (Exception exception) {
                    try {
                        reset.close();
                    }
                    catch (SQLException sQLException) {
                        // empty catch block
                    }
                    try {
                        pstmt.close();
                    }
                    catch (SQLException sQLException) {
                        // empty catch block
                    }
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
            finally {
                try {
                    reset.close();
                }
                catch (SQLException sQLException) {}
                try {
                    pstmt.close();
                }
                catch (SQLException sQLException) {}
                try {
                    conn.close();
                }
                catch (SQLException sQLException) {}
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block24: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            String pass = request.getParameter("pass") != null ? request.getParameter("pass") : "";
            String check = request.getParameter("check");
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet reset = null;
            String sql1 = "select * from member where mbid= ? and mbpass=?";
            DBManager dbmanager = new DBManager();
            PrintWriter out = response.getWriter();
            try {
                try {
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1, id);
                    pstmt.setString(2, pass);
                    reset = pstmt.executeQuery();
                    if (reset.next()) {
                        String email = reset.getString("mbemail");
                        Cookie cookie1 = new Cookie("id", id);
                        Cookie cookie2 = new Cookie("check", check);
                        cookie1.setMaxAge(86400);
                        cookie2.setMaxAge(86400);
                        response.addCookie(cookie1);
                        response.addCookie(cookie2);
                        session.setAttribute("id", (Object)id);
                        session.setAttribute("pass", (Object)pass);
                        session.setAttribute("email", (Object)email);
                        session.setMaxInactiveInterval(86400);
                        out.print("<script>alert('반갑습니다. 회원님.'); location.href='http://adnerwin.cafe24.com/project0709/cal/calendar?id=" + id + "';</script>");
                        break block24;
                    }
                    out.print("<script>alert('아이디와 비밀번호를 확인해주세요.'); location.href='javascript:history.go(-1)';</script>");
                }
                catch (Exception exception) {
                    try {
                        reset.close();
                    }
                    catch (SQLException sQLException) {
                        // empty catch block
                    }
                    try {
                        pstmt.close();
                    }
                    catch (SQLException sQLException) {
                        // empty catch block
                    }
                    try {
                        conn.close();
                    }
                    catch (SQLException sQLException) {}
                }
            }
            finally {
                try {
                    reset.close();
                }
                catch (SQLException sQLException) {}
                try {
                    pstmt.close();
                }
                catch (SQLException sQLException) {}
                try {
                    conn.close();
                }
                catch (SQLException sQLException) {}
            }
        }
    }
}
