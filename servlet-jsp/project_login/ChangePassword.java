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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ChangePassword"})
public class ChangePassword
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String mbpass = request.getParameter("mbpass");
        String pass = request.getParameter("pass");
        boolean check = true;
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet reset = null;
        String sql = "select *from member where mbpass = ?";
        String sql1 = "update member set mbpass = ? where mbpass= ?";
        DBManager dbmanager = new DBManager();
        PrintWriter out = response.getWriter();
        try {
            try {
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, mbpass);
                reset = pstmt.executeQuery();
                while (reset.next()) {
                    reset.close();
                    pstmt.close();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1, pass);
                    pstmt.setString(2, mbpass);
                    result = pstmt.executeUpdate();
                    if (result <= -1) continue;
                    check = false;
                }
                if (check) {
                    out.print("<script>alert('비밀번호를 확인해주세요.');</script>");
                    out.print("<script>location.href='javascript:history.go(-1)';</script>");
                }
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
