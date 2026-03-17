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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/GoogleLogin"})
public class GoogleLogin
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mbid;
        boolean done;
        block26: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            done = false;
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet reset = null;
            String email = request.getParameter("email");
            String sql = "select * from member where mbemail='" + email + "'";
            mbid = "";
            DBManager dbmanager = new DBManager();
            try {
                try {
                    con = dbmanager.getConnection();
                    pstmt = con.prepareStatement(sql);
                    reset = pstmt.executeQuery();
                    if (reset.next()) {
                        mbid = reset.getString("mbid");
                        done = true;
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
                        con.close();
                    }
                    catch (SQLException sQLException) {}
                    break block26;
                }
            }
            catch (Throwable throwable) {
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
                    con.close();
                }
                catch (SQLException sQLException) {
                    // empty catch block
                }
                throw throwable;
            }
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
                con.close();
            }
            catch (SQLException sQLException) {
                // empty catch block
            }
        }
        if (done) {
            response.sendRedirect("http://adnerwin.cafe24.com/test/loginProcess?mbid=" + mbid);
        } else {
            response.sendRedirect("http://adnerwin.cafe24.com/test/loginBoard/join_agree.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
