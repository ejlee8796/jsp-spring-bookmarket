/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  javax.servlet.ServletException
 *  javax.servlet.annotation.WebServlet
 *  javax.servlet.http.HttpServlet
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package project_login;

import com.company.db.DBManager;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/KakaoLogin"})
public class KakaoLogin
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block30: {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            String client_id = "{KAKAO_API_KEY}";
            String code = request.getParameter("code");
            String redirect_uri = URLEncoder.encode("http://adnerwin.cafe24.com/test/KakaoLogin", "UTF-8");
            String requestURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&code=" + code;
            try {
                URL url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                int responseCode = conn.getResponseCode();
                BufferedReader br = null;
                br = responseCode == 200 ? new BufferedReader(new InputStreamReader(conn.getInputStream())) : new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                String inputLines = null;
                StringBuffer res = new StringBuffer();
                while ((inputLines = br.readLine()) != null) {
                    res.append(inputLines);
                }
                br.close();
                String access_token = "";
                if (responseCode != 200) break block30;
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject)jsonParser.parse(res.toString());
                access_token = jsonObject.get("access_token").getAsString();
                String header = "Bearer " + access_token;
                try {
                    boolean done;
                    String id;
                    block32: {
                        String inputLine1;
                        String apiURL1 = "https://kapi.kakao.com/v1/user/me";
                        URL url1 = new URL(apiURL1);
                        HttpURLConnection conn1 = (HttpURLConnection)url1.openConnection();
                        conn1.setRequestMethod("POST");
                        conn1.setRequestProperty("Authorization", header);
                        int responseCode1 = conn1.getResponseCode();
                        BufferedReader br1 = responseCode1 == 200 ? new BufferedReader(new InputStreamReader(conn1.getInputStream())) : new BufferedReader(new InputStreamReader(conn1.getErrorStream()));
                        StringBuffer response1 = new StringBuffer();
                        while ((inputLine1 = br1.readLine()) != null) {
                            response1.append(inputLine1);
                        }
                        br1.close();
                        JsonParser jsonParser1 = new JsonParser();
                        JsonObject jsonObject1 = (JsonObject)jsonParser1.parse(response1.toString());
                        id = jsonObject1.get("id").getAsString() != null ? jsonObject1.get("id").getAsString() : "";
                        done = false;
                        Connection con = null;
                        PreparedStatement pstmt = null;
                        ResultSet reset = null;
                        String sql = "select * from member where mbid='" + id + "'";
                        DBManager dbmanager = new DBManager();
                        try {
                            try {
                                con = dbmanager.getConnection();
                                pstmt = con.prepareStatement(sql);
                                reset = pstmt.executeQuery();
                                if (reset.next()) {
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
                                break block32;
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
                        response.sendRedirect("http://adnerwin.cafe24.com/test/loginProcess?mbid=" + id);
                        break block30;
                    }
                    response.sendRedirect("http://adnerwin.cafe24.com/test/loginBoard/join_agree.jsp?mbid=" + id);
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
