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

public class BDeleteCommand
implements BCommand {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        BDao dao = new BDao();
        int bno = dao.fbDelete(Integer.parseInt(request.getParameter("bno")));
        PrintWriter out = response.getWriter();
        if (bno > -1) {
            out.print("<script>alert('글 삭제를 완료했습니다.'); location.href='index1view1/eventList.jsp';</script>");
        } else {
            out.print("<script>alert('관리자에게 문의바랍니다.'); location.href=history.go(-1);</script>");
        }
    }
}
