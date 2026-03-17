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
package project_list;

import com.company.db.DBManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/BPaging"})
public class BPaging
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String uri = request.getParameter("uri");
        String joinUri = request.getParameter("joinUri") != null ? request.getParameter("joinUri") : "";
        JsonArray list = null;
        int listcount = 0;
        int onepagelimit = 0;
        int pagetotal = 0;
        int bottomlimit = 0;
        int pstartno = 0;
        int currentpage = 0;
        int startpage = 0;
        int endpage = 0;
        try {
            PrintWriter out = response.getWriter();
            listcount = this.showAllListCnt(uri);
            onepagelimit = 10;
            pagetotal = (int)Math.ceil((float)listcount / (float)onepagelimit);
            bottomlimit = 10;
            if (request.getParameter("pstartno") != null) {
                pstartno = Integer.parseInt(request.getParameter("pstartno"));
            }
            list = joinUri.equals("") ? this.showAllList(pstartno, onepagelimit, uri) : this.showJoinList(pstartno, onepagelimit, uri, joinUri);
            currentpage = (int)Math.ceil((float)(pstartno + 1) / (float)onepagelimit);
            startpage = (int)Math.floor((float)(currentpage - 1) / (float)bottomlimit) * bottomlimit + 1;
            endpage = startpage + bottomlimit - 1;
            if (pagetotal < endpage) {
                endpage = pagetotal;
            }
            JsonObject jobject = new JsonObject();
            jobject.addProperty("startpage", (Number)startpage);
            jobject.addProperty("endpage", (Number)endpage);
            jobject.addProperty("onepagelimit", (Number)onepagelimit);
            jobject.addProperty("pagetotal", (Number)pagetotal);
            jobject.addProperty("currentpage", (Number)currentpage);
            jobject.addProperty("bottomlimit", (Number)bottomlimit);
            jobject.addProperty("listcount", (Number)listcount);
            list.add((JsonElement)jobject);
            out.print(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public int showAllListCnt(String uri) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet reset = null;
        int count = 0;
        String sql2 = "select count(*) cnt from " + uri;
        try {
            DBManager dbmanager = new DBManager();
            conn = dbmanager.getConnection();
            pstmt = conn.prepareStatement(sql2);
            reset = pstmt.executeQuery();
            if (reset.next()) {
                count = reset.getInt("cnt");
            }
            reset.close();
            pstmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public JsonArray showAllList(int a, int b, String uri) {
        JsonArray arr = new JsonArray();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet reset = null;
        String sql2 = "select * from " + uri + " order by bno desc limit ?, ?";
        try {
            DBManager dbmanager = new DBManager();
            conn = dbmanager.getConnection();
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            reset = pstmt.executeQuery();
            Gson gson = new Gson();
            while (reset.next()) {
                JsonObject jobject = new JsonObject();
                jobject.addProperty("bno", (Number)reset.getInt("bno"));
                jobject.addProperty("btitle", reset.getString("btitle"));
                jobject.addProperty("bname", reset.getString("bname"));
                jobject.addProperty("bhit", (Number)reset.getInt("bhit"));
                jobject.addProperty("bdate", reset.getString("bdate").split(" ")[0]);
                arr.add((JsonElement)jobject);
            }
            reset.close();
            pstmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }

    public JsonArray showJoinList(int a, int b, String uri, String joinUri) {
        JsonArray arr = new JsonArray();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet reset = null;
        String sql = "select a.bno, a.bhit, b.bfile, a.btitle, a.bdate, a.bcontent  from " + uri + " a, " + joinUri + " b where a.bno = b.bno order by a.bno desc limit ?, ?";
        try {
            DBManager dbmanager = new DBManager();
            conn = dbmanager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            reset = pstmt.executeQuery();
            Gson gson = new Gson();
            while (reset.next()) {
                JsonObject jobject = new JsonObject();
                jobject.addProperty("bno", (Number)reset.getInt("bno"));
                jobject.addProperty("bfile", reset.getString("bfile"));
                jobject.addProperty("btitle", reset.getString("btitle"));
                jobject.addProperty("bdate", reset.getString("bdate"));
                jobject.addProperty("bcontent", reset.getString("bcontent"));
                jobject.addProperty("bhit", (Number)reset.getInt("bhit"));
                arr.add((JsonElement)jobject);
            }
            reset.close();
            pstmt.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
}
