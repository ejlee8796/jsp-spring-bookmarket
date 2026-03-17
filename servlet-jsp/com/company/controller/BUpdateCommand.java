/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.oreilly.servlet.MultipartRequest
 *  com.oreilly.servlet.multipart.DefaultFileRenamePolicy
 *  com.oreilly.servlet.multipart.FileRenamePolicy
 *  javax.servlet.ServletContext
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.company.controller;

import com.company.controller.BCommand;
import com.company.dao.BDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BUpdateCommand
implements BCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        ServletContext uploadFile = request.getServletContext();
        String uploadFilePath = uploadFile.getRealPath("/upload");
        int uploadFileSizeLimit = 0xA00000;
        MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, "utf-8", (FileRenamePolicy)new DefaultFileRenamePolicy());
        int bno = Integer.parseInt(multi.getParameter("bno"));
        String btitle = multi.getParameter("title");
        String bcontent = multi.getParameter("content");
        String file1 = multi.getFilesystemName("uploadFile1") != null ? multi.getFilesystemName("uploadFile1") : multi.getParameter("uploadFile1name");
        String file2 = multi.getFilesystemName("uploadFile2") != null ? multi.getFilesystemName("uploadFile2") : multi.getParameter("uploadFile2name");
        String bdate = String.valueOf(multi.getParameter("startdate")) + " ~ " + multi.getParameter("enddate");
        BDao dao = new BDao();
        int result1 = dao.fbUpdate(btitle, bcontent, file1, bdate, bno);
        dao = new BDao();
        int result2 = dao.fbUpdate(file2, bno);
        PrintWriter out = response.getWriter();
        if (result1 > -1 && result2 > -1) {
            out.print("<script>alert('글 수정을 완료했습니다.'); location.href='detail.basic?bno=" + bno + "';</script>");
        } else {
            out.print("<script>alert('관리자에게 문의바랍니다.'); location.href=history.go(-1);</script>");
        }
    }
}
