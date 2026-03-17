/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  javax.servlet.http.HttpServletResponse
 */
package com.company.controller;

import com.company.controller.BCommand;
import com.company.dao.BDao;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BPrevCommand
implements BCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        BDao dao = new BDao();
        int bno = Integer.parseInt(request.getParameter("bno"));
        int result = dao.fbPrev(bno);
        PrintWriter out = response.getWriter();
        if (result == 0) {
            out.print("<script>alert('더 이상 글이 없습니다.'); location.href=history.go(-1);</script>");
        } else {
            out.print("<script>location.href='detail.basic?bno=" + result + "';</script>");
        }
    }
}
