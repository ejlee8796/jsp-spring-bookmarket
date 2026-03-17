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

@WebServlet(value={"/Iddouble"})
public class Iddouble
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block27: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet reset = null;
            String mbid = request.getParameter("mbid") != null ? request.getParameter("mbid").trim() : null;
            String mbemail = request.getParameter("mbemail") != null ? request.getParameter("mbemail").trim() : "";
            String sql = "";
            sql = mbid != null ? "select *from member where mbid = ?" : "select *from member where mbemail = ?";
            DBManager dbmanager = new DBManager();
            PrintWriter out = response.getWriter();
            boolean check = true;
            try {
                try {
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    if (mbid != null) {
                        pstmt.setString(1, mbid);
                    } else {
                        pstmt.setString(1, mbemail);
                    }
                    reset = pstmt.executeQuery();
                    while (reset.next()) {
                        check = false;
                    }
                    if (check) {
                        out.print("<span style='color:blue'>사용가능</span>");
                        break block27;
                    }
                    out.print("<span style='color:red'>사용불가</span>");
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
        this.doGet(request, response);
    }
}
