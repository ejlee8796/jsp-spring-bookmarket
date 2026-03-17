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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/SearchTest"})
public class SearchTest
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        boolean check = true;
        String keyword = request.getParameter("keyword");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;
        String sql = "select *from notice1board1 where btitle like '%" + keyword + "%'";
        PrintWriter out = response.getWriter();
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                stmt = conn.createStatement();
                rset = stmt.executeQuery(sql);
                while (rset.next()) {
                    out.print("<tr><td><a href=\"../BDetail?bno=" + rset.getInt("bno") + "&uri=notice1board1\">" + rset.getString("btitle") + "</td></tr>");
                    check = false;
                }
                if (check) {
                    out.print(String.valueOf(keyword) + "에 관한 질문이 없어요!<br><a href='../loginBoard/footer_2.jsp'><strong>1:1문의</strong></a>는 어떠세요?");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    rset.close();
                    stmt.close();
                    conn.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }
        }
        finally {
            try {
                rset.close();
                stmt.close();
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
