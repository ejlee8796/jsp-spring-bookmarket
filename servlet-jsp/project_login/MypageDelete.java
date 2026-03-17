/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value={"/MypageDelete"})
public class MypageDelete
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String mbno = request.getParameter("mbno");
        Connection conn = null;
        Statement pstmt = null;
        ResultSet reset = null;
        int result = 0;
        String sql = "select *from member where mbno=?";
        String sql1 = "delete from member where mbno=?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(mbno));
                reset = pstmt.executeQuery();
                while (reset.next()) {
                    int no = reset.getInt("mbno");
                    reset.close();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(1, no);
                    result = pstmt.executeUpdate();
                    PrintWriter out = response.getWriter();
                    if (result <= -1) continue;
                    HttpSession session = request.getSession();
                    session.invalidate();
                    out.print("<script>alert('회원탈퇴를 완료합니다.');");
                    out.print("location.href='index.jsp';</script>");
                }
            }
            catch (Exception exception) {
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
