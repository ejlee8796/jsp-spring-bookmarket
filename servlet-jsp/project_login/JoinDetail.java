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
import com.company.dto.MDto;
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

@WebServlet(value={"/JoinDetail"})
public class JoinDetail
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String mbid = request.getParameter("mbid");
        String join = request.getParameter("join") != null ? request.getParameter("join") : "";
        Connection conn = null;
        Statement pstmt = null;
        ResultSet reset = null;
        String sql = "select * from member where mbid=?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, mbid);
                reset = pstmt.executeQuery();
                MDto dto = new MDto();
                while (reset.next()) {
                    int no = reset.getInt("mbno");
                    String id = reset.getString("mbid");
                    String pass = reset.getString("mbpass");
                    String gender = reset.getString("mbgender");
                    String email = reset.getString("mbemail");
                    String mobile = reset.getString("mbmobile");
                    String address = reset.getString("mbaddress");
                    String answer = reset.getString("mbagree");
                    String like = reset.getString("mblike");
                    String date = reset.getString("mbdate");
                    String ip = reset.getString("mbip");
                    dto = new MDto(no, id, pass, gender, email, mobile, address, like, answer, date, ip);
                    request.setAttribute("dto", (Object)dto);
                    PrintWriter out = response.getWriter();
                    if (join.equals("naver")) {
                        out.print("<script>alert('회원가입이 완료 되었습니다.');</script>");
                    }
                    out.print("<script>location.href='loginBoard/login.jsp';</script>");
                }
            }
            catch (Exception exception) {
                try {
                    reset.close();
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
                reset.close();
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
        this.doGet(request, response);
    }
}
