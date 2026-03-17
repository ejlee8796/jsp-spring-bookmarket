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
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/ModifyProcess"})
public class ModifyProcess
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String id = request.getParameter("id");
        String pass = request.getParameter("pass");
        String gender = request.getParameter("gender");
        gender = gender.contentEquals("woman") ? "여자" : "남자";
        String[] arrEmail = request.getParameterValues("email");
        String email = String.valueOf(arrEmail[0]) + "@" + arrEmail[1];
        String[] arrPhone = request.getParameterValues("phone");
        String mobile = String.valueOf(arrPhone[0]) + "-" + arrPhone[1] + "-" + arrPhone[2];
        String[] arrPost = request.getParameterValues("post");
        String post = String.valueOf(arrPost[0]) + "-" + arrPost[1];
        String[] arrAddress = request.getParameterValues("address");
        String address = String.valueOf(arrAddress[0]) + " , " + arrAddress[1];
        String answer = request.getParameter("answer");
        String[] arrSelect = request.getParameterValues("select");
        String select = "";
        int i = 0;
        while (i < arrSelect.length) {
            if (arrSelect[i].equals("novle")) {
                arrSelect[i] = "문학";
            } else if (arrSelect[i].equals("comic")) {
                arrSelect[i] = "만화";
            } else if (arrSelect[i].equals("social")) {
                arrSelect[i] = "사회";
            } else if (arrSelect[i].equals("religion")) {
                arrSelect[i] = "종교";
            }
            select = String.valueOf(select) + arrSelect[i] + " ";
            ++i;
        }
        boolean check = true;
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet reset = null;
        String sql = "select *from member where mbpass = ?";
        String sql1 = "update member set mbgender=?, mbemail=?, mbmobile=?, mbaddress=?, mblike=?, mbagree=? where mbpass= ?";
        DBManager dbmanager = new DBManager();
        PrintWriter out = response.getWriter();
        try {
            try {
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, pass);
                reset = pstmt.executeQuery();
                while (reset.next()) {
                    reset.close();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setString(1, gender);
                    pstmt.setString(2, email);
                    pstmt.setString(3, mobile);
                    pstmt.setString(4, String.valueOf(post) + ", " + address);
                    pstmt.setString(5, select);
                    pstmt.setString(6, answer);
                    pstmt.setString(7, pass);
                    result = pstmt.executeUpdate();
                    if (result <= -1) continue;
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Mypage?mbid=" + id + "&page=mypage");
                    dispatcher.forward((ServletRequest)request, (ServletResponse)response);
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
