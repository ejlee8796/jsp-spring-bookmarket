/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.oreilly.servlet.MultipartRequest
 *  com.oreilly.servlet.multipart.DefaultFileRenamePolicy
 *  com.oreilly.servlet.multipart.FileRenamePolicy
 *  javax.servlet.ServletContext
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_list;

import com.company.db.DBManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/BUpdate"})
public class BUpdate
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block33: {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            int bno = Integer.parseInt(request.getParameter("bno"));
            int result = 0;
            ServletContext uploadFile = this.getServletContext();
            String uploadFilePath = uploadFile.getRealPath("/upload");
            int uploadFileSizeLimit = 0xA00000;
            MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, "utf-8", (FileRenamePolicy)new DefaultFileRenamePolicy());
            String uri = multi.getParameter("uri");
            String bpass = multi.getParameter("bpassword");
            String btitle = multi.getParameter("btitle");
            String bcontent = multi.getParameter("bcontent").replace("\r\n", "<br>");
            String bfilename = multi.getParameter("fileName");
            String fileName = multi.getFilesystemName("uploadFile");
            if (fileName != null) {
                request.setAttribute("fileName", (Object)fileName);
            } else {
                fileName = bfilename;
            }
            String sql1 = "select bpass from " + uri + " where bno =?";
            String sql2 = "update " + uri + " set btitle=?, bcontent=?, bfile=? where bno=?";
            Connection conn = null;
            Statement pstmt = null;
            ResultSet reset = null;
            String pass = "";
            try {
                try {
                    DBManager dbmanager = new DBManager();
                    conn = dbmanager.getConnection();
                    pstmt = conn.prepareStatement(sql1);
                    pstmt.setInt(1, bno);
                    reset = pstmt.executeQuery();
                    while (reset.next()) {
                        pass = reset.getString("bpass");
                    }
                    reset.close();
                    pstmt.close();
                    PrintWriter out = response.getWriter();
                    if (bpass.equals(pass)) {
                        pstmt = conn.prepareStatement(sql2);
                        pstmt.setString(1, btitle);
                        pstmt.setString(2, bcontent);
                        pstmt.setString(3, fileName);
                        pstmt.setInt(4, bno);
                        result = pstmt.executeUpdate();
                        if (uri.equals("notice1board1")) {
                            response.sendRedirect("BDetail?bno=" + bno + "&uri=" + uri + "&result=" + result);
                        }
                        break block33;
                    }
                    out.print("<script> alert('비밀번호를 확인해주세요.'); history.go(-1); </script>");
                }
                catch (Exception exception) {
                    if (reset != null) {
                        try {
                            reset.close();
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
                    if (conn == null) break block33;
                    try {
                        conn.close();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            finally {
                if (reset != null) {
                    try {
                        reset.close();
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
}
