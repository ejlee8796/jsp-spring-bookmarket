/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Controller
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RequestMethod
 */
package com.company.spring0716;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Download {
    private static final Logger logger = LoggerFactory.getLogger(Download.class);

    @RequestMapping(value={"/download"}, method={RequestMethod.GET})
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("...........downloading");
        PrintWriter print = response.getWriter();
        request.setCharacterEncoding("UTF-8");
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
        print.println("<script>history.go(-1); </script>");
    }
}
