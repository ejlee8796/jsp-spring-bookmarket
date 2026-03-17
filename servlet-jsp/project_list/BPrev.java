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
package project_list;

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

@WebServlet(value={"/BPrev"})
public class BPrev
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block30: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            int bno = Integer.parseInt(request.getParameter("bno"));
            String uri = request.getParameter("uri");
            Connection conn = null;
            Statement pstmt = null;
            ResultSet rset = null;
            String sql = "select bno from " + uri + " where ? < bno order by bno limit 1";
            int prvbno = 0;
            try {
                try {
                    DBManager dbmanager = new DBManager();
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, bno);
                    rset = pstmt.executeQuery();
                    if (rset.next()) {
                        prvbno = rset.getInt("bno");
                        if (uri.equals("notice1board1")) {
                            response.sendRedirect("BDetail?bno=" + prvbno + "&uri=" + uri);
                        }
                        break block30;
                    }
                    PrintWriter out = response.getWriter();
                    out.print("<script>alert('이전 글이 존재하지 않습니다.');</script>");
                    out.print("<meta http-equiv='refresh' content='0; url=BDetail?bno=" + bno + "&uri=" + uri + "'>");
                }
                catch (Exception exception) {
                    if (rset != null) {
                        try {
                            rset.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        }
                        catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn == null) break block30;
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                if (rset != null) {
                    try {
                        rset.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
