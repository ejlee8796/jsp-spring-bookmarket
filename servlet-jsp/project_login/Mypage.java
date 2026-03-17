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
package project_login;

import com.company.db.DBManager;
import com.company.dto.MDto;
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

@WebServlet(value={"/Mypage"})
public class Mypage
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String mbid = request.getParameter("mbid");
        String page = request.getParameter("page");
        Connection conn = null;
        Statement pstmt = null;
        ResultSet reset = null;
        String sql = "select *from member where mbid=?";
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
                    RequestDispatcher dispatcher = null;
                    if (page.equals("modify")) {
                        dispatcher = request.getRequestDispatcher("loginBoard/mypage_modify_form.jsp");
                    } else if (page.equals("mypage")) {
                        dispatcher = request.getRequestDispatcher("loginBoard/mypage.jsp");
                    }
                    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
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
