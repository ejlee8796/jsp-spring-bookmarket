/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_index;

import com.company.db.DBManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

@WebServlet(value={"/Cservlet"})
public class Cservlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cno;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        JsonArray list = null;
        String writer = "";
        String password = "";
        String secret = "";
        String comment = "";
        String cpost = request.getParameter("cpost");
        String check = request.getParameter("check") != null ? request.getParameter("check") : "";
        int n = cno = request.getParameter("cno") != null ? Integer.parseInt(request.getParameter("cno")) : -1;
        if (check.equals("delete")) {
            PrintWriter out = response.getWriter();
            try {
                list = this.delete(cpost, cno);
                out.print(list);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else if (check.equals("confirm")) {
            PrintWriter out = response.getWriter();
            try {
                list = this.delete(cpost, cno);
                out.print(list);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (request.getParameter("id") != null) {
                writer = request.getParameter("id");
                password = "nope";
                secret = request.getParameter("secret") != null ? request.getParameter("secret") : "n";
                comment = request.getParameter("content").replace("\r\n", "<br>");
            }
            PrintWriter out = response.getWriter();
            try {
                if (!writer.equals("")) {
                    this.InsertComment(writer, password, comment, secret, cpost);
                }
                list = this.showList(cpost);
                out.print(list);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void InsertComment(String writer, String password, String comment, String secret, String cpost) {
        Connection conn = null;
        Statement pstmt = null;
        int reset = 0;
        String sql = "insert into index1board3comment (bno, writer, pass, comment, secret, cpost) values (1,?,?,?,?,?)";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, writer);
                pstmt.setString(2, password);
                pstmt.setString(3, comment);
                pstmt.setString(4, secret);
                pstmt.setString(5, cpost);
                reset = pstmt.executeUpdate();
                if (reset != -1) {
                    System.out.println("등록성공!");
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

    public JsonArray delete(String writer, int cno) {
        JsonArray arr = new JsonArray();
        Connection conn = null;
        Statement pstmt = null;
        int result = 0;
        String sql = "delete from index1board3comment where writer = ? and cno = ?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, writer);
                pstmt.setInt(2, cno);
                result = pstmt.executeUpdate();
                if (result > -1) {
                    JsonObject jobject = new JsonObject();
                    jobject.addProperty("alert", "삭제를 성공했습니다.");
                    arr.add((JsonElement)jobject);
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
        return arr;
    }

    public JsonArray showList(String cpost) {
        JsonArray arr = new JsonArray();
        Connection conn = null;
        Statement pstmt = null;
        ResultSet reset = null;
        String sql = "select * from index1board3comment where cpost = ?";
        try {
            try {
                DBManager dbmanager = new DBManager();
                conn = dbmanager.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, cpost);
                reset = pstmt.executeQuery();
                Gson gson = new Gson();
                while (reset.next()) {
                    JsonObject jobject = new JsonObject();
                    jobject.addProperty("writer", reset.getString("writer"));
                    jobject.addProperty("cno", (Number)reset.getInt("cno"));
                    jobject.addProperty("pass", reset.getString("pass"));
                    jobject.addProperty("secret", reset.getString("secret"));
                    jobject.addProperty("comment", reset.getString("comment"));
                    jobject.addProperty("date", reset.getString("cdate"));
                    arr.add((JsonElement)jobject);
                }
                reset.close();
                pstmt.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                try {
                    reset.close();
                }
                catch (SQLException e2) {
                    e2.printStackTrace();
                }
                try {
                    pstmt.close();
                }
                catch (SQLException e3) {
                    e3.printStackTrace();
                }
                try {
                    conn.close();
                }
                catch (SQLException e4) {
                    e4.printStackTrace();
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
        return arr;
    }
}
