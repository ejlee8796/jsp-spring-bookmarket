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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value={"/download"})
public class download
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter print = response.getWriter();
        String root = request.getSession().getServletContext().getRealPath("/");
        String savedPath = String.valueOf(root) + "upload";
        String filename = "myCal.zip";
        String originfilename = "myCal.zip";
        FileInputStream in = null;
        OutputStream out = null;
        File file = null;
        boolean skip = false;
        String client = "";
        try {
            file = new File(savedPath, filename);
            in = new FileInputStream(file);
        }
        catch (FileNotFoundException e) {
            skip = true;
        }
        client = request.getHeader("User-Agent");
        response.reset();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=myCal.zip");
        if (!skip) {
            if (client.indexOf("MSIE") != -1) {
                response.setHeader("Content-Disposition", "attachment; filename=" + new String(originfilename.getBytes("KSC5601"), "ISO8859_1"));
            } else {
                originfilename = new String(originfilename.getBytes("utf-8"), "iso-8859-1");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + originfilename + "\"");
                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
            }
            response.setHeader("Content-Length", "" + file.length());
            out = response.getOutputStream();
            byte[] b = new byte[(int)file.length()];
            int leng = 0;
            while ((leng = ((InputStream)in).read(b)) > 0) {
                out.write(b, 0, leng);
            }
        } else {
            print.println("<script>alert('파일을 찾을 수 없습니다.'); history.go(-1); </script>");
        }
        ((InputStream)in).close();
        out.close();
        print.println("<script> location.href='history.go(-1);' </script>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
