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
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/BWrite"})
public class BWrite
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletContext uploadFile = this.getServletContext();
        String uploadFilePath = uploadFile.getRealPath("/upload");
        int uploadFileSizeLimit = 0xA00000;
        MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, "utf-8", (FileRenamePolicy)new DefaultFileRenamePolicy());
        String uri = multi.getParameter("uri");
        String bname = multi.getParameter("name");
        String bpassword = multi.getParameter("password") != null ? multi.getParameter("password") : multi.getParameter("uri2");
        String btitle = multi.getParameter("title");
        String bcontent = multi.getParameter("content").replace("\r\n", "<br>");
        String bfileName = multi.getFilesystemName("uploadFile") != null ? multi.getFilesystemName("uploadFile") : "";
        String file1 = null;
        String file2 = null;
        String filename1 = null;
        String filename2 = null;
        if (bfileName.equals("")) {
            Enumeration files = multi.getFileNames();
            file1 = (String)files.nextElement();
            filename1 = multi.getFilesystemName("uploadFile1") != null ? multi.getFilesystemName("uploadFile1") : "";
            file2 = (String)files.nextElement();
            filename2 = multi.getFilesystemName("uploadFile2") != null ? multi.getFilesystemName("uploadFile2") : "";
        }
        int bgroup = 0;
        int bstep = 0;
        String bdate = multi.getParameter("startdate") != null ? String.valueOf(multi.getParameter("startdate")) + " ~ " + multi.getParameter("enddate") : "now()";
        InetAddress local = InetAddress.getLocalHost();
        String ip = local.getHostAddress();
        Connection conn = null;
        Statement pstmt = null;
        ResultSet rset = null;
        boolean check = true;
        String sql1 = "select max(bstep) as bstep from " + uri;
        String sql2 = "";
        if (bdate.equals("now()")) {
            sql2 = "insert into " + uri + " (bname, bpass, btitle, bcontent, bfile, bgroup, bstep, bip, bdate) values (?,?,?,?,?,?,?,?," + bdate + ")";
        } else {
            sql2 = "insert into " + uri + " (bname, bpass, btitle, bcontent, bfile, bgroup, bstep, bip, bdate) values (?,?,?,?,?,?,?,?,'" + bdate + "')";
            bfileName = filename1;
        }
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql1);
                rset = pstmt.executeQuery();
                if (rset.next()) {
                    bstep = rset.getInt("bstep");
                    bgroup = (int)Math.ceil(bstep / 1000) + 1;
                    bstep = (int)(Math.ceil(bstep / 1000) * 1000.0 + 1000.0);
                    check = false;
                } else {
                    bstep = 0;
                }
                pstmt.close();
                if (check) {
                    bgroup = (int)Math.ceil(bstep / 1000) + 1;
                    bstep = (int)(Math.ceil(bstep / 1000) * 1000.0 + 1000.0);
                }
                pstmt = conn.prepareStatement(sql2);
                pstmt.setString(1, bname);
                pstmt.setString(2, bpassword);
                pstmt.setString(3, btitle);
                pstmt.setString(4, bcontent);
                pstmt.setString(5, bfileName);
                pstmt.setInt(6, bgroup);
                pstmt.setInt(7, bstep);
                pstmt.setString(8, ip);
                int reset = pstmt.executeUpdate();
                if (reset != -1) {
                    if (uri.equals("notice1board1")) {
                        response.sendRedirect("notice1board1/footer_1.jsp?reset=" + reset);
                    } else if (uri.equals("index1view1")) {
                        this.JoinWrite(bpassword, filename2);
                        response.sendRedirect("index1view1/eventList.jsp?reset=" + reset);
                    }
                }
            }
            catch (Exception exception) {
                try {
                    rset.close();
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
                rset.close();
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

    protected void JoinWrite(String uri, String bfile) {
        Connection conn = null;
        Statement pstmt = null;
        int reset = 0;
        String sql = "insert into " + uri + " (bfile) values (?)";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, bfile);
                reset = pstmt.executeUpdate();
                if (reset > -1) {
                    System.out.println("입력성공");
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    pstmt.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    conn.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
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
