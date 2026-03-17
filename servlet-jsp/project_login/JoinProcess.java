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
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/JoinProcess"})
public class JoinProcess
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block26: {
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
            InetAddress local = InetAddress.getLocalHost();
            String ip = local.getHostAddress();
            Connection conn = null;
            Statement pstmt = null;
            String sql = "insert into member ( mbid, mbpass, mbgender, mbemail, mbmobile, mbaddress, mblike, mbagree, mbip ) values (?,?,?,?,?,?,?,?,?)";
            int result = 0;
            try {
                try {
                    DBManager dbmanager = new DBManager();
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, id);
                    pstmt.setString(2, pass);
                    pstmt.setString(3, gender);
                    pstmt.setString(4, email);
                    pstmt.setString(5, mobile);
                    pstmt.setString(6, String.valueOf(post) + ", " + address);
                    pstmt.setString(7, select);
                    pstmt.setString(8, answer);
                    pstmt.setString(9, ip);
                    result = pstmt.executeUpdate();
                    PrintWriter out = response.getWriter();
                    if (result > 0) {
                        out.print("<script>alert('회원가입이 완료 되었습니다.'); location.href='JoinDetail?mbid=" + id + "';</script>");
                        break block26;
                    }
                    out.print("<script>alert('회원가입에 실패했습니다.'); location.href='javascript:history.go(-1)';</script>");
                }
                catch (Exception e1) {
                    e1.printStackTrace();
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
    }
}
