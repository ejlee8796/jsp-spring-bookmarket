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
package project_index;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/NaverBooksSearch"})
public class NaverBooksSearch
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        String search = request.getParameter("search");
        String sort = request.getParameter("sort");
        String clientId = "{NAVER_API_CLIENT_ID}";
        String clientSecret = "{NAVER_API_SECRET}";
        try {
            String inputLine;
            String text = URLEncoder.encode(search, "UTF-8");
            String stext = URLEncoder.encode(sort, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/book?query=" + text + "&display=100&sort=" + stext;
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br = responseCode == 200 ? new BufferedReader(new InputStreamReader(con.getInputStream())) : new BufferedReader(new InputStreamReader(con.getErrorStream()));
            StringBuffer response1 = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response1.append(inputLine);
            }
            br.close();
            out.println(response1.toString());
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
