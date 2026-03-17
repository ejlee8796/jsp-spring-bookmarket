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
import java.net.InetAddress;
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

@WebServlet(value={"/NaverLogin"})
public class NaverLogin
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        block32: {
            String clientId = "{NAVER_API_CLIENT_ID}";
            String clientSecret = "{NAVER_API_SECRET}";
            String code = request.getParameter("code");
            String state = request.getParameter("state");
            String redirectURI = URLEncoder.encode("http://adnerwin.cafe24.com/test/loginBoard/loginProcess", "UTF-8");
            String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
            apiURL = String.valueOf(apiURL) + "client_id=" + clientId;
            apiURL = String.valueOf(apiURL) + "&client_secret=" + clientSecret;
            apiURL = String.valueOf(apiURL) + "&redirect_uri=" + redirectURI;
            apiURL = String.valueOf(apiURL) + "&code=" + code;
            apiURL = String.valueOf(apiURL) + "&state=" + state;
            try {
                String inputLine;
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode();
                BufferedReader br = responseCode == 200 ? new BufferedReader(new InputStreamReader(con.getInputStream())) : new BufferedReader(new InputStreamReader(con.getErrorStream()));
                StringBuffer res = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    res.append(inputLine);
                }
                br.close();
                if (responseCode != 200) break block32;
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = (JsonObject)jsonParser.parse(res.toString());
                String token = jsonObject.get("access_token").getAsString();
                String header = "Bearer " + token;
                try {
                    boolean done;
                    String id;
                    block34: {
                        String inputLine1;
                        String apiURL1 = "https://openapi.naver.com/v1/nid/me";
                        URL url1 = new URL(apiURL1);
                        HttpURLConnection conn = (HttpURLConnection)url1.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Authorization", header);
                        int responseCode1 = conn.getResponseCode();
                        BufferedReader br1 = responseCode1 == 200 ? new BufferedReader(new InputStreamReader(conn.getInputStream())) : new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        StringBuffer response1 = new StringBuffer();
                        while ((inputLine1 = br1.readLine()) != null) {
                            response1.append(inputLine1);
                        }
                        br1.close();
                        JsonParser jsonParser1 = new JsonParser();
                        JsonObject jsonObject1 = (JsonObject)jsonParser1.parse(response1.toString());
                        String response2 = jsonObject1.get("response").toString();
                        JsonParser jsonParser2 = new JsonParser();
                        JsonObject jsonObject2 = (JsonObject)jsonParser2.parse(response2.toString());
                        id = jsonObject2.get("id").getAsString();
                        done = false;
                        Connection con1 = null;
                        PreparedStatement pstmt = null;
                        ResultSet reset = null;
                        String sql = "select * from member where mbid='" + id + "'";
                        DBManager dbmanager = new DBManager();
                        try {
                            try {
                                con1 = dbmanager.getConnection();
                                pstmt = con1.prepareStatement(sql);
                                reset = pstmt.executeQuery();
                                if (reset.next()) {
                                    done = true;
                                } else {
                                    pstmt.close();
                                    InetAddress local = InetAddress.getLocalHost();
                                    String ip = local.getHostAddress();
                                    int result = 0;
                                    sql = "insert into member ( mbid, mbpass, mbgender, mbemail, mbmobile, mbaddress, mblike, mbagree, mbip ) values (?,' ',' ',' ',' ',' ',' ',' ',?)";
                                    pstmt = con1.prepareStatement(sql);
                                    pstmt.setString(1, id);
                                    pstmt.setString(2, ip);
                                    result = pstmt.executeUpdate();
                                    if (result > 0) {
                                        done = false;
                                    }
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
                                    con1.close();
                                }
                                catch (SQLException sQLException) {}
                                break block34;
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
                                con1.close();
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
                            con1.close();
                        }
                        catch (SQLException sQLException) {
                            // empty catch block
                        }
                    }
                    if (done) {
                        response.sendRedirect("http://adnerwin.cafe24.com/test/loginProcess?mbid=" + id);
                        break block32;
                    }
                    response.sendRedirect("http://adnerwin.cafe24.com/test/JoinDetail?mbid=" + id + "&join=naver");
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
